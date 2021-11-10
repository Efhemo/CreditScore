# CreditScore


## Summary
The app pulls credit score information content from ClearScore API. It is built according to the `Model-View-ViewModel`(MVVM) architecture.
## Architecture

The application follows clean architecture because of the benefits it brings to software which includes scalability, maintainability and testability.
It enforces separation of concerns and dependency inversion, where higher and lower level layers all depend on abstractions. In the project, the layers are separated into different package namely:

- ui (as feature package)
- Domain
- Remote (data-layer)


Although, Taking advantage of gradle module representing the layers instead of packages would have enforces a robust separation of concerns. These packages are full of kotlin codes except ui packages. The reason being that the low level layers need to be independent of the Android framework. One of the key points of clean architecture is that low level layers should be platform agnostic. As a result, the domain and data layers can be plugged into a kotlin multiplatform project for example, and it will run just fine because we don't depend on the android framework.
The remote layers is an implementation details that can be provided in any form (Firebase, GraphQl server, REST, ROOM, SQLDelight, etc) as long as it conforms to the business rules / contracts defined in the data layer which in turn also conforms to contracts defined in domain.

For dependency injection and asynchronous programming, the project uses Dagger Hilt and Coroutines. Dagger Hilt is a fine abstraction over the vanilla dagger boilerplate, and is easy to setup. Coroutines brings kotlin's expressibility and conciseness to asynchronous programming, along with a fine suite of operators that make it a robust solution. 

#### UI
The ViewModel which is the presenter implementation is very lean. The reason for using the `Jetpack ViewModel` is that it survives configuration changes, and thus ensures that the view state is persisted across screen rotation.

MVVM is a good architecture to use when you don't want any surprises in user experience as state only comes from one source and is immutable.

#### Domain
The domain layer contains the app business logic. It defines contracts for data operations and domain models to be used in the app. All other layers have their own representation of these domain models, and Mapper classes (or adapters) are used to transform the domain models to each layer's domain model representation.
Writing mappers and models can take a lot of effort and result in boilerplate, but they make the codebase much more maintainable and robust by separating concerns.

#### Data
The Data layer implements the contract for providing data defined in the domain layer, and it in turn provides a contract that will be used to fetch data from the datasources.
We have only one data source - `Remote`. Remote relies on Retrofit library to fetch data from the binlist.net REST api.

## Features
* Clean Architecture with MVVM
* Kotlin Coroutines with Flow
* Dagger Hilt
* Jetpack Compose
* Kotlin Gradle DSL

## Prerequisite
To build this project, you require:
- Android Studio Arctic Fox | 2020.3.1 Patch 3
- Gradle 7.0+



## Libraries
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md)
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Truth](https://truth.dev/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [Kotlin coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Dagger Hilt](https://dagger.dev/hilt)
- [Kotlin Gradle DSL](https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin)
