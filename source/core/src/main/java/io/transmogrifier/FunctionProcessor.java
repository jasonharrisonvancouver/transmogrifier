/*
 * Copyright (C) 2019 Terratap Technology Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.transmogrifier;

import java.util.function.Function;

/**
 * An adapter to make a Function act as a Processor
 *
 * @param <I> input
 * @param <O> output
 * @see java.util.function.Function
 */
public class FunctionProcessor<I, O>
        implements Processor<I, Void, O>
{
    /**
     * The function to wrap.
     */
    private final Function<I, O> function;

    /**
     * Construct a FunctionProcessor with the specified function.
     *
     * @param func the function to wrap
     */
    public FunctionProcessor(final Function<I, O> func)
    {
        if(func == null)
        {
            throw new RuntimeException("func cannot be null");
        }

        function = func;
    }

    /**
     * Perform the process by calling apply on the input and returning the result.
     *
     * @param input the input parameter
     * @return the result
     * @throws RuntimeException if something goes wrong with the processing
     */
    public O perform(final I input,
                     final Void extra)
            throws
            ProcessorException
    {
        try
        {
            return function.apply(input);
        }
        catch(final RuntimeException ex)
        {
            throw new ProcessorException(ex.getMessage(),
                                         ex);
        }
    }
}
