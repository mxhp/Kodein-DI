package org.kodein.di.erased

import org.kodein.di.Kodein
import org.kodein.di.test.FixMethodOrder
import org.kodein.di.test.IPerson
import org.kodein.di.test.MethodSorters
import org.kodein.di.test.Person
import kotlin.test.Test
import kotlin.test.assertEquals

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ErasedTests_06_Constant {

    @Test
    fun test_00_ConstantBindingGetInstance() {

        val kodein = Kodein {
            constant(tag = "answer") with 42
        }

        val c: Int by kodein.instance(tag = "answer")
        val answer: Int by kodein.constant()

        assertEquals(42, c)
        assertEquals(42, answer)
    }

    @Test
    fun test_01_ConstantBindingGetProvider() {

        val kodein = Kodein {
            constant(tag = "answer") with 42
        }

        val c: () -> Int by kodein.provider(tag = "answer")

        assertEquals(42, c())
    }

    @Test
    fun test_02_ConstantBindingGetPolymorphic() {

        val kodein = Kodein {
            constant(tag = "salomon") with Person("Salomon") as IPerson
        }

        val p: IPerson by kodein.instance(tag = "salomon")
        val salomon: IPerson by kodein.constant()

        assertEquals(Person("Salomon"), p)
        assertEquals(Person("Salomon"), salomon)
    }


}
