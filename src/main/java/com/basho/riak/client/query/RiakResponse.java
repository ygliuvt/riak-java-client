/*
 * Copyright 2013 Basho Technologies Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basho.riak.client.query;

import com.basho.riak.client.util.ByteArrayWrapper;

public abstract class RiakResponse
{

    private final ByteArrayWrapper key;
    private final ByteArrayWrapper bucketType;
    private final ByteArrayWrapper bucketName;

    protected RiakResponse(ByteArrayWrapper bucketName, ByteArrayWrapper key, ByteArrayWrapper bucketType)
    {
        this.key = key;
        this.bucketType = bucketType;
        this.bucketName = bucketName;
    }

    public ByteArrayWrapper getKey()
    {
        return key;
    }

    public ByteArrayWrapper getBucketType()
    {
        return bucketType;
    }

    public ByteArrayWrapper getBucketName()
    {
        return bucketName;
    }

    public static class Builder
    {
        private ByteArrayWrapper key;
        private ByteArrayWrapper bucketName;
        private ByteArrayWrapper bucketType =
            ByteArrayWrapper.unsafeCreate("default".getBytes());

        public Builder(ByteArrayWrapper bucketName, ByteArrayWrapper key)
        {
            if (null == bucketName || bucketName.length() == 0 || null == key || key.length() == 0)
            {
                throw new IllegalArgumentException("Bucket name and key cannot be null or zero length");
            }
            this.bucketName = bucketName;
            this.key = key;
        }

        public Builder withBucketType(ByteArrayWrapper bucketType)
        {
            if (bucketType != null)
            {
                if (bucketType.length() == 0)
                {
                    throw new IllegalArgumentException("Bucket type cannot be null or zero length");
                }
                else
                {
                    this.bucketType = bucketType;
                }
            }
            return this;
        }
    }

}
