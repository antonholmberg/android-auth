/*
 * Copyright (c) 2015-2016 Spotify AB
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.sdk.android.auth;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class PKCEInformation implements Parcelable {

    private final String mVerifier;
    private final String mChallenge;
    private final String mCodeChallengeMethod;

    private PKCEInformation(String verifier, String challenge, String challengeMethod) {
        mVerifier = verifier;
        mChallenge = challenge;
        mCodeChallengeMethod = challengeMethod;
    }

    public static PKCEInformation sha256(String verifier, String challenge) {
        return new PKCEInformation(verifier, challenge, "S256");
    }

    public String getVerifier() {
        return mVerifier;
    }

    public String getChallenge() {
        return mChallenge;
    }

    public String getCodeChallengeMethod() {
        return mCodeChallengeMethod;
    }

    private PKCEInformation(Parcel in) {
        mVerifier = in.readString();
        mChallenge = in.readString();
        mCodeChallengeMethod = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mVerifier);
        dest.writeString(mChallenge);
        dest.writeString(mCodeChallengeMethod);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PKCEInformation> CREATOR = new Creator<PKCEInformation>() {
        @Override
        public PKCEInformation createFromParcel(Parcel in) {
            return new PKCEInformation(in);
        }

        @Override
        public PKCEInformation[] newArray(int size) {
            return new PKCEInformation[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PKCEInformation that = (PKCEInformation) o;
        return Objects.equals(mVerifier, that.mVerifier) &&
                Objects.equals(mChallenge, that.mChallenge) &&
                Objects.equals(mCodeChallengeMethod, that.mCodeChallengeMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mVerifier, mChallenge, mCodeChallengeMethod);
    }
}
