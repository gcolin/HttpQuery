## Features

* Http request in a single line
* Internally stream are always closed at the end of the request
* Get the response in POJO with JAXB, Xstream or Jackson

See the project httpquery-examples

## Building with Gradle

Install a recent version of Gradle (tested with Gradle 2.6)

### Configure build.gradle

If you have a local Nexus OSS change nothing. Else update the repositories.

### Build


    gradle install uploadArtifacts