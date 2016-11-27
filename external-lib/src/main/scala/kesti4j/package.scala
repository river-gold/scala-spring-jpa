package kesti4j

import java.util.function._

/**
  * @author sunghyouk.bae@gmail.com
  */
package object core {

    object Implicits {

        implicit def toBiConsumer[@miniboxed T, @miniboxed U](op: (T, U) => Unit): BiConsumer[T, U] = {
            new BiConsumer[T, U] {
                override def accept(t: T, u: U): Unit = op.apply(t, u)
            }
        }

        implicit def toBiFunction[@miniboxed T, @miniboxed U, @miniboxed R](op: (T, U) => R): BiFunction[T, U, R] = {
            new BiFunction[T, U, R] {
                override def apply(t: T, u: U): R = op.apply(t, u)
            }
        }

        implicit def toBiPredicate[@miniboxed T, @miniboxed U](op: (T, U) => Boolean): BiPredicate[T, U] = {
            new BiPredicate[T, U] {
                override def test(t: T, u: U): Boolean = op.apply(t, u)
            }
        }

        implicit def toBooleanSupplier(op: => Boolean): BooleanSupplier = {
            new BooleanSupplier {
                override def getAsBoolean: Boolean = op
            }
        }

        implicit def toConsumer[@miniboxed T](op: T => Unit): Consumer[T] = {
            new Consumer[T] {
                override def accept(t: T): Unit = op.apply(t)
            }
        }

        implicit def toDoubleBinaryOperator(op: (Double, Double) => Double): DoubleBinaryOperator = {
            new DoubleBinaryOperator {
                override def applyAsDouble(left: Double, right: Double): Double = op.apply(left, right)
            }
        }

        implicit def toDoubleConsumer(op: Double => Unit): DoubleConsumer = {
            new DoubleConsumer {
                override def accept(value: Double): Unit = op.apply(value)
            }
        }

        implicit def toDoubleFunction[@miniboxed R](op: Double => R): DoubleFunction[R] = {
            new DoubleFunction[R] {
                override def apply(value: Double): R = op.apply(value)
            }
        }

        implicit def toDoublePredicate(op: Double => Boolean): DoublePredicate = {
            new DoublePredicate {
                override def test(value: Double): Boolean = op.apply(value)
            }
        }

        implicit def toDoubleSupplier(op: => Double): DoubleSupplier = {
            new DoubleSupplier {
                override def getAsDouble: Double = op
            }
        }

        implicit def toDoubleToIntFunction(op: Double => Int): DoubleToIntFunction = {
            new DoubleToIntFunction {
                override def applyAsInt(value: Double): Int = op.apply(value)
            }
        }

        implicit def toDoubleToLongFunction(op: Double => Long): DoubleToLongFunction = {
            new DoubleToLongFunction {
                override def applyAsLong(value: Double): Long = op.apply(value)
            }
        }

        implicit def toDoubleUnaryOperator(op: Double => Double): DoubleUnaryOperator = {
            new DoubleUnaryOperator {
                override def applyAsDouble(operand: Double): Double = op.apply(operand)
            }
        }

        implicit def toFunction[@miniboxed T, @miniboxed R](op: T => R): Function[T, R] = {
            new Function[T, R] {
                override def apply(t: T): R = op.apply(t)
            }
        }

        // region << Int >>

        implicit def toIntBinaryOperator(op: (Int, Int) => Int): IntBinaryOperator = {
            new IntBinaryOperator {
                override def applyAsInt(left: Int, right: Int): Int = op.apply(left, right)
            }
        }

        implicit def toIntConsumer(op: Int => Unit): IntConsumer = {
            new IntConsumer {
                override def accept(value: Int): Unit = op.apply(value)
            }
        }

        implicit def toIntFunction[@miniboxed R](op: Int => R): java.util.function.IntFunction[R] = {
            new IntFunction[R] {
                override def apply(value: Int): R = op.apply(value)
            }
        }

        implicit def toIntPredicate(op: Int => Boolean): IntPredicate = {
            new IntPredicate {
                override def test(value: Int): Boolean = op.apply(value)
            }
        }

        implicit def toIntSupplier(op: => Int): IntSupplier = {
            new IntSupplier {
                override def getAsInt: Int = op
            }
        }

        implicit def toIntToDoubleFunction(op: Int => Double): IntToDoubleFunction = {
            new IntToDoubleFunction {
                override def applyAsDouble(value: Int): Double = op.apply(value)
            }
        }

        implicit def toIntToLongFunction(op: Int => Long): IntToLongFunction = {
            new IntToLongFunction {
                override def applyAsLong(value: Int): Long = op.apply(value)
            }
        }

        implicit def toIntUnaryOperator(op: Int => Int): IntUnaryOperator = {
            new IntUnaryOperator {
                override def applyAsInt(operand: Int): Int = op.apply(operand)
            }
        }

        // endregion

        // region << Int >>

        implicit def toLongBinaryOperator(op: (Long, Long) => Long): LongBinaryOperator = {
            new LongBinaryOperator {
                override def applyAsLong(left: Long, right: Long): Long = op.apply(left, right)
            }
        }

        implicit def toLongConsumer(op: Long => Unit): LongConsumer = {
            new LongConsumer {
                override def accept(value: Long): Unit = op.apply(value)
            }
        }

        implicit def toLongFunction[@miniboxed R](op: Long => R): java.util.function.LongFunction[R] = {
            new LongFunction[R] {
                override def apply(value: Long): R = op.apply(value)
            }
        }

        implicit def toLongPredicate(op: Long => Boolean): LongPredicate = {
            new LongPredicate {
                override def test(value: Long): Boolean = op.apply(value)
            }
        }

        implicit def toLongSupplier(op: => Long): LongSupplier = {
            new LongSupplier {
                override def getAsLong: Long = op
            }
        }

        implicit def toLongToDoubleFunction(op: Long => Double): LongToDoubleFunction = {
            new LongToDoubleFunction {
                override def applyAsDouble(value: Long): Double = op.apply(value)
            }
        }

        implicit def toLongToIntFunction(op: Long => Int): LongToIntFunction = {
            new LongToIntFunction {
                override def applyAsInt(value: Long): Int = op.apply(value)
            }
        }

        implicit def toLongUnaryOperator(op: Long => Long): LongUnaryOperator = {
            new LongUnaryOperator {
                override def applyAsLong(operand: Long): Long = op.apply(operand)
            }
        }

        // endregion

        implicit def toObjDoubleConsumer[@miniboxed T](op: (T, Double) => Unit): ObjDoubleConsumer[T] = {
            new ObjDoubleConsumer[T] {
                override def accept(t: T, value: Double): Unit = op.apply(t, value)
            }
        }

        implicit def toObjIntConsumer[@miniboxed T](op: (T, Int) => Unit): ObjIntConsumer[T] = {
            new ObjIntConsumer[T] {
                override def accept(t: T, value: Int): Unit = op.apply(t, value)
            }
        }

        implicit def toPredicate[@miniboxed T](op: T => Boolean): Predicate[T] = {
            new Predicate[T] {
                override def test(t: T): Boolean = op.apply(t)
            }
        }

        implicit def toSupplier[@miniboxed T](op: => T): Supplier[T] = {
            new Supplier[T] {
                override def get(): T = op
            }
        }

        implicit def toToDoubleBiFunction[@miniboxed T, @miniboxed U](op: (T, U) => Double): ToDoubleBiFunction[T, U] = {
            new ToDoubleBiFunction[T, U] {
                override def applyAsDouble(t: T, u: U): Double = op.apply(t, u)
            }
        }

        implicit def toToDoubleFunction[@miniboxed T](op: T => Double): ToDoubleFunction[T] = {
            new ToDoubleFunction[T] {
                override def applyAsDouble(value: T): Double = op.apply(value)
            }
        }

        implicit def toToIntBiFunction[@miniboxed T, @miniboxed U](op: (T, U) => Int): ToIntBiFunction[T, U] = {
            new ToIntBiFunction[T, U] {
                override def applyAsInt(t: T, u: U): Int = op.apply(t, u)
            }
        }

        implicit def toToIntFunction[@miniboxed T](op: T => Int): ToIntFunction[T] = {
            new ToIntFunction[T] {
                override def applyAsInt(value: T): Int = op.apply(value)
            }
        }

        implicit def toToLongBiFunction[@miniboxed T, @miniboxed U](op: (T, U) => Long): ToLongBiFunction[T, U] = {
            new ToLongBiFunction[T, U] {
                override def applyAsLong(t: T, u: U): Long = op.apply(t, u)
            }
        }

        implicit def toToLongFunction[@miniboxed T](op: T => Long): ToLongFunction[T] = {
            new ToLongFunction[T] {
                override def applyAsLong(value: T): Long = op.apply(value)
            }
        }

        implicit def toUnaryOperator[@miniboxed T](op: (T) => T): UnaryOperator[T] = {
            new UnaryOperator[T] {
                override def apply(t: T): T = op.apply(t)
            }
        }
    }

}
