/*
 * Copyright (c) 2007, 2017 Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.jemmy.input;

import org.jemmy.interfaces.*;
import org.jemmy.interfaces.Caret.Direction;

/**
 *
 * @author shura
 */
public abstract class AbstractCaretOwner implements CaretOwner {
    private double error;

    public double allowedError() {
        return error;
    }

    public void allowError(double error) {
        if(error < 0) {
            throw new IllegalArgumentException("Precision could not be less than 0");
        }
        this.error = error;
    }

    public void to(double position) {
        caret().to(new ToPosition(this, position, error));
    }

    public static class ToPosition implements Direction {

        private double error;
        private double value;
        private CaretOwner caret;

        public ToPosition(CaretOwner caret, double value, double allowedError) {
            this.caret = caret;
            this.value = value;
            this.error = allowedError;
        }

        public ToPosition(CaretOwner caret, double value) {
            this(caret, value, 0);
        }

        public int to() {
            double diff = diff();
            return (Math.abs(diff) <= error) ? 0 : ((diff > 0) ? 1 : -1);
        }

        @Override
        public String toString() {
            return "value == " + position() + " with " + error + " error";
        }

        protected double diff() {
            return position() - caret.position();
        }

        public CaretOwner getCaret() {
            return caret;
        }

        protected double position() {
            return value;
        }
    }

}
