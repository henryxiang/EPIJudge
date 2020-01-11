package epi

import spock.lang.Specification
import spock.lang.Unroll

class ListUtilsTest extends Specification {
    @Unroll
    def "find element by index"() {
        expect:
        ListNode<Integer> head = ListUtils.createList(a as int[])
        ListNode<Integer> node = ListUtils.index(head, index)
        node.data == value

        where:
        a << [
                [1, 2, 3],
                [1]
        ]
        index << [1, 0]
        value << [2, 1]
    }

    @Unroll
    def "create list from arrays"() {
        expect:
        ListNode<Integer> head = ListUtils.createList(a as int[])
        ListUtils.length(head) == len

        where:
        a << [
                [],
                [1, 2, 3],
                [1]
        ]
        len << [0, 3, 1]
    }

    @Unroll
    def "prepend list"() {
        expect:
        def data = a
        ListNode<Integer> list = null
        for (int d : data)
            list = ListUtils.prepend(list, new ListNode<Integer>(d, null))
        ListNode<Integer> l = ListUtils.createList(data as int[])
        ListNode<Integer> result = ListUtils.reverseList(l)
        ListUtils.isEqual(list, result)

        where:
        a << [
                [1, 2, 3],
                [1]
        ]
    }
}
