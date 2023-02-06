package com.harel.practice.tdd;

import com.harel.practise.tdd.Node;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class NodeTest {

    @Test
    public void shouldReturnNullOnEmptyString() {
        assertThat(Node.deserialize(""), nullValue());
    }

    @Test
    public void deserializeSingleNode() {
        assertThat(Node.deserialize("{5}"), is(Node.builder()
            .value(5)
            .build()));
    }

    @Test
    public void deserializeNodeWithSingleDepthLeftNode() {
        assertThat(Node.deserialize("{{5}6}"), is(Node.builder()
            .left(Node.builder()
                .value(5)
                .build())
            .value(6)
            .build()));
    }

    @Test
    public void deserializeNodeWithSingleDepthRightNode() {
        assertThat(Node.deserialize("{6{5}}"), is(Node.builder()
            .value(6)
            .right(Node.builder()
                .value(5)
                .build())
            .build()));
    }

    @Test
    public void deserializeNodeWithSingleLeftAndSingleRightNodes() {
        assertThat(Node.deserialize("{{5}6{7}}"), is(Node.builder()
            .left(Node.builder()
                .value(5)
                .build())
            .value(6)
            .right(Node.builder()
                .value(7)
                .build())
            .build()));
    }

    @Test
    public void deserializeNodeWithTwoDepthLeftNodes() {
        assertThat(Node.deserialize("{{{4}5}6}"), is(Node.builder()
            .left(Node.builder()
                .left(Node.builder().value(4).build())
                .value(5)
                .build())
            .value(6)
            .build()));
    }

    @Test
    public void deserializeNodeWithTwoDepthRightNodes() {
        assertThat(Node.deserialize("{6{5{4}}}"), is(Node.builder()
            .value(6)
            .right(Node.builder()
                .right(Node.builder().value(4).build())
                .value(5)
                .build())
            .build()));
    }

    @Test
    public void sanityTest() {
        assertThat(Node.deserialize("{{{{5}34{9}}56{78}}56{{5}34{9}}}"), is(
            Node.builder()
                .left(Node.builder()
                    .left(Node.builder()
                        .left(Node.builder()
                            .value(5)
                            .build())
                        .right(Node.builder()
                            .value(9)
                            .build())
                        .value(34)
                        .build())
                    .right(Node.builder().
                        value(78)
                        .build())
                    .value(56)
                    .build())
                .right(Node.builder()
                    .left(Node.builder()
                        .value(5)
                        .build())
                    .right(Node.builder()
                        .value(9)
                        .build())
                    .value(34)
                    .build())
                .value(56)
                .build()
        ));
    }
}
