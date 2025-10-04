package de.fabianrump.crossnotes.ui.feature.addtodo

import de.fabianrump.crossnotes.data.model.Priority
import de.fabianrump.crossnotes.domain.usecase.holidays.FetchHolidaysUseCase
import de.fabianrump.crossnotes.domain.usecase.todo.AddTodoUseCase
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.ChangePriority
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.ChangeText
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.DismissDatePicker
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.OpenDatePicker
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoIntent.SaveTodo
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoLabel.ShowErrorSnackbar
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.Error
import de.fabianrump.crossnotes.ui.feature.addtodo.AddTodoResult.Loading
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class AddTodoExecutorTest {

    private lateinit var addTodoUseCase: AddTodoUseCase
    private lateinit var fetchHolidaysUseCase: FetchHolidaysUseCase
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: TestScope
    private lateinit var dispatch: (AddTodoResult) -> Unit
    private lateinit var publish: (AddTodoLabel) -> Unit
    private lateinit var executor: AddTodoExecutor

    @BeforeTest
    fun setup() {
        addTodoUseCase = mockk()
        fetchHolidaysUseCase = mockk()
        testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
        dispatch = mockk(relaxed = true)
        publish = mockk(relaxed = true)

        executor = AddTodoExecutor(
            addTodoUseCase = addTodoUseCase,
            fetchHolidaysUseCase = fetchHolidaysUseCase,
            scope = testScope,
            dispatch = dispatch,
            publish = publish
        )
    }

    @Test
    fun `changeText should dispatch ChangeText result`() {
        val text = "New Todo Text"

        executor.execute(ChangeText(text = text))

        verify { dispatch(AddTodoResult.ChangeText(text = text)) }
    }

    @Test
    fun `changePriority should dispatch ChangePriority result`() {
        val priority = Priority.HIGH

        executor.execute(ChangePriority(priority = priority))

        verify { dispatch(AddTodoResult.ChangePriority(priority = priority)) }
    }

    @Test
    fun `openDatePicker should dispatch OpenDatePicker result`() {
        executor.execute(OpenDatePicker)

        verify { dispatch(AddTodoResult.OpenDatePicker) }
    }

    @Test
    fun `dismissDatePicker should dispatch DismissDatePicker result`() {
        executor.execute(DismissDatePicker)

        verify { dispatch(AddTodoResult.DismissDatePicker) }
    }

    @Test
    fun `saveTodo with failure should dispatch error and show snackbar`() = runTest {
        val text = "Buy groceries"
        val priority = Priority.MEDIUM
        val dueDate = LocalDate(2025, 10, 15)
        val exception = Exception("Database error")

        coEvery { addTodoUseCase(any()) } throws exception

        executor.execute(
            SaveTodo(
                text = text,
                priority = priority,
                dueDate = dueDate
            )
        )
        testScope.advanceUntilIdle()

        verifyOrder {
            dispatch(Loading)
            dispatch(Error(message = "Database error"))
            publish(ShowErrorSnackbar(message = "Failed to load notes"))
        }
    }

    @Test
    fun `saveTodo with unknown error should dispatch Unknown error message`() = runTest {
        val text = "Buy groceries"
        val priority = Priority.MEDIUM
        val dueDate = LocalDate(2025, 10, 15)

        coEvery { addTodoUseCase(any()) } throws Exception()

        executor.execute(
            SaveTodo(
                text = text,
                priority = priority,
                dueDate = dueDate
            )
        )
        testScope.advanceUntilIdle()

        verify { dispatch(Error(message = "Unknown error")) }
    }

    @Test
    fun `multiple intents should be dispatched correctly`() {
        executor.execute(ChangeText(text = "Test"))
        executor.execute(ChangePriority(priority = Priority.LOW))
        executor.execute(OpenDatePicker)

        verify { dispatch(AddTodoResult.ChangeText(text = "Test")) }
        verify { dispatch(AddTodoResult.ChangePriority(priority = Priority.LOW)) }
        verify { dispatch(AddTodoResult.OpenDatePicker) }
    }
}