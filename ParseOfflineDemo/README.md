Parse Offline Demo
==================

This demo uses Parse as both cloud storage and an offline
datastore to synchronize both in-memory objects
(`SoundGram`'s, `SoundGramSample`'s, and user score objects)
and soundgrams (.3gp files recorded locally on an Android
device). For a description of these objects and their
corresponding concepts, refer to the [SoundGram README](https://github.com/invisible-college/soundgram).
The in-memory objects possess references to each
other and to the soundgram files via their `LocalId`s.

`LocalId`s are 10-character sequences of random
alphanumeric characters that are meant to be unique,
although this is never enforced anywhere. They follow the exact same format as Parse `ObjectId`'s, except we generate them locally for objects *before* they are synced.
Since Parse doesn't accept new objects with non-null `ObjectId`s not assigned by the server, we store these in a separate field called `localId`, and these are used to create local metadata and to allow objects to reference each other.

SoundGram objects just contain the display name.

| objectId | localId | DisplayName | Shortname|
|------------|--------|-------------|----------|
| "Rcieyk1I9Z" |"oRPFLy61SY"|"Some day when I wake up" | "Someday"| 

`SoundGramSample` objects contain a reference to their
parent `SoundGram` object and their corresponding ParseFile
object via its `LocalId` (*not* its
`ObjectId`.

| objectId | localId | speakerName | parentId | fileId |
|----------|---------|-------------|----------|--------|
| "pEi5rgBj5Q" | "Tz0cHuzh4c" | "Ender" | "oRPFLy61SY" |

### Unresolved Questions:
How do ParseFile's show up in the Parse Dashboard?
Do they have an `objectId` field, and can they have custom
other fields like `localId`'?

## Use Cases

We test the following use cases in this demo and our
own local (Open) parse server deployed to heroku.

### Pinning Locally

When objects are created locally, they are pinned offline
so that they can be accessed even if the network connection
goes away, or was never there to begin with. We pin in the
background to prevent blocking, but we usually don't provide a callback because we don't care when pinning is finished.

`newObj.pinInBackbround()`

### Uploading

New objects attempt to save themselves to the cloud, but if the network connection isn't present, the Parse client does not save this and reattempt later. However, with pinning, we can still persist these unsynced local objects and manually try again later.

`newObj.saveInBackbround()`

### Offline Querying

When the app starts up, or at regular intervals when we detect that the network connection is present again, we query objects in our offline datastore. If they have a null `ObjectId`, we know they were never sync'ed, and we can attempt another save.



### Syncing

When we have a network connection, we should periodically sync from the cloud (or receive a push notification). We don't necessarily want to pin these new objects indiscriminately. The logic is TBD if we choose to pin it, since we may only want to pins objects that are relevant to the user (for which we would need to represent the user's "tastes" or "preferences")

### Uploading/Download Files

Related to the above, we need to upload the soundgram `.3gp`
files that correspond to soundgrams or download them. This is done with `ParseFile`'s.
