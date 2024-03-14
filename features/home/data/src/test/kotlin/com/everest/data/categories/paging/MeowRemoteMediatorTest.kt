package com.everest.data.categories.paging


/**
# RemoteMediator tests

The goal of the `RemoteMediator` unit tests is to verify that the `load()` function returns the correct `MediatorResult`.
Tests for side effects, such as data being inserted into the database, are better suited for integration tests.
####
## The first step is to determine what dependencies your `RemoteMediator` implementation needs.
The following example demonstrates a RemoteMediator implementation that requires a Room database, a Retrofit interface, and a search string:

You can provide the `Retrofit` interface and the search string as demonstrated in the `PagingSource` tests section.
Providing a mock version of the Room database is very involved, so it can be easier to provide an in-memory implementation of the database instead of a full mock version.
Because creating a Room database requires a `Context` object, you must place this `RemoteMediator` test in the androidTest directory and execute it with the AndroidJUnit4 test runner so that it has access to a test application context.

Define tear-down functions to ensure that state does not leak between test functions. This ensures consistent results between test runs.
####
## The next step is to test the `load()` function. In this example, there are three cases to test:

- The first case is when `mockApi` returns valid data. The `load()` function should return `MediatorResult.Success`, and the `endOfPaginationReached` property should be `false`.
- The second case is when `mockApi` returns a successful response, but the returned data is empty. The `load()` function should return `MediatorResult.Success`, and the `endOfPaginationReached` property should be `true`.
- The third case is when `mockApi` throws an `exception` when fetching the data. The `load()` function should return `MediatorResult.Error`.

### Follow these steps to test the first case:

- Set up the `mockApi` with the post data to return.
- Initialize the `RemoteMediator` object.
- Test the `load()` function.

 ## The second test requires the `mockApi` to return an empty result. Because you clear the data from the `mockApi` after each test run, it will return an empty result by default.
 ## The final test requires the `mockApi` to throw an `exception` so that the test can verify that the `load()` function correctly returns `MediatorResult.Error`.

 */
class MeowRemoteMediatorTest
