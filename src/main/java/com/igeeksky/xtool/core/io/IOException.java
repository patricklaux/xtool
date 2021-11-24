/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.io;

/**
 * IO异常
 * <p>
 * {@link java.io.IOException} 转换为 {@link RuntimeException}
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-22
 */
public class IOException extends RuntimeException {

    /**
     * @param message 异常信息
     */
    public IOException(String message) {
        super(message);
    }

    /**
     * @param cause IO异常
     */
    public IOException(java.io.IOException cause) {
        super(cause);
    }

    /**
     * @param message 异常信息
     * @param cause   IO异常
     */
    public IOException(String message, java.io.IOException cause) {
        super(message, cause);
    }

}
