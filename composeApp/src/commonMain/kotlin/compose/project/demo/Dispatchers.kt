package compose.project.demo

import kotlinx.coroutines.CoroutineDispatcher

expect val ioDispatcher: CoroutineDispatcher
expect val defaultDispatcher: CoroutineDispatcher
