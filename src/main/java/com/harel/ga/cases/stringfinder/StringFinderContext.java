package com.harel.ga.cases.stringfinder;

import com.harel.ga.alg.AlgorithmContext;
import lombok.Value;

@Value
public class StringFinderContext implements AlgorithmContext {
    String stringToCalc;
}
