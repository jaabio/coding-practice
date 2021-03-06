package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring

data class TestCase(val fromPegPosition: PegPosition,
                    val toPegPosition: PegPosition,
                    val ringCount: Int = 0,
                    val expectedOperationCount: Int? = null)

fun pegsFromRunningOperations(
        ringCount: Int,
        fromPosition: PegPosition,
        operations: List<TowersOfHanoi.RingMove<Ring>>): Pegs<Ring> {
    return pegsFromRunningOperations(
            (1..ringCount).toList(), fromPosition, operations)
}

fun pegsFromRunningOperations(
        rings: List<Int>,
        fromPosition: PegPosition,
        operations: List<TowersOfHanoi.RingMove<Ring>>,
        constraint: (from: Peg<Ring>, to: Peg<Ring>, ring: Ring) -> Unit = { _, _, _ -> }): Pegs<Ring> {
    val pegs = Pegs<Ring>(rings, fromPosition)
    operations.forEach { (fromPosition, toPosition) ->
        pegs.at(toPosition).let { toPeg ->
            pegs.at(fromPosition).let { fromPeg ->
                val ring = fromPeg.pop()
                toPeg.push(ring)
                constraint(fromPeg, toPeg, ring)
            }
        }
    }
    return pegs
}
