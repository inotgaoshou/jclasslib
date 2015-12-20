/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.structures.constants;

import org.gjt.jclasslib.structures.ConstantType;
import org.gjt.jclasslib.structures.InvalidByteCodeException;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
    Describes a <tt>CONSTANT_Long_info</tt> constant pool data structure.
 
    @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
*/
public class ConstantLongInfo extends ConstantLargeNumeric {

    public ConstantType getConstantType() {
        return ConstantType.CONSTANT_LONG;
    }

    public String getVerbose() throws InvalidByteCodeException {
        return String.valueOf(getLong());
    }

    /**
        Get the long value of this constant pool entry.
        @return the value
     */
    public long getLong() {
        return ((long)getHighBytes() << 32) | ((long)getLowBytes() & 0xFFFFFFFF);
    }

    /**
        Set the long value of this constant pool entry.
        @param number the value
     */
    public void setLong(long number) {
        setHighBytes((int)(number >>> 32));
        setLowBytes((int)(number & 0xFFFFFFFF));
    }

    public void read(DataInput in)
        throws InvalidByteCodeException, IOException {
        
        super.read(in);
        if (isDebug()) debug("read ");
    }
    
    public void write(DataOutput out)
        throws InvalidByteCodeException, IOException {
        
        out.writeByte(ConstantType.CONSTANT_LONG.getTag());
        super.write(out);
        if (isDebug()) debug("wrote ");
    }
    
    protected void debug(String message) {
        super.debug(message + getConstantType() + " with high_bytes " + getHighBytes() +
              " and low_bytes " + getLowBytes());
    }

}