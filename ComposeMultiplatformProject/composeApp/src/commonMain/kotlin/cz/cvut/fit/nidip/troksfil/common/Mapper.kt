package cz.cvut.fit.nidip.troksfil.common

interface Mapper<I, O> {
    fun map(input: I): O
}