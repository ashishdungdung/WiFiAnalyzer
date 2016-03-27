/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.vrem.wifianalyzer.wifi.band;

import android.support.v4.util.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class WiFiChannelsGHZ_5Test {
    private WiFiChannels fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new WiFiChannelsGHZ_5();
    }

    @Test
    public void testGetWiFiChannelByFrequency() throws Exception {
        assertEquals(36, fixture.getWiFiChannelByFrequency(5180).getChannel());
        assertEquals(165, fixture.getWiFiChannelByFrequency(5825).getChannel());
    }

    @Test
    public void testGetWiFiChannelByFrequencyFail() throws Exception {
        assertEquals(WiFiChannel.UNKNOWN, fixture.getWiFiChannelByFrequency(5167));
        assertEquals(WiFiChannel.UNKNOWN, fixture.getWiFiChannelByFrequency(5828));
    }

    @Test
    public void testGetWiFiChannelFirst() throws Exception {
        assertEquals(7, fixture.getWiFiChannelFirst().getChannel());
    }

    @Test
    public void testGetWiFiChannelLast() throws Exception {
        assertEquals(196, fixture.getWiFiChannelLast().getChannel());
    }

    @Test
    public void testGetFrequencySpread() throws Exception {
        assertEquals(10, fixture.getFrequencySpread());
    }

    @Test
    public void testGetFrequencyOffset() throws Exception {
        assertEquals(20, fixture.getFrequencyOffset());
    }

    @Test
    public void testGetChannelsSet() throws Exception {
        assertEquals(5, fixture.getWiFiChannelPairs().size());
        validatePair(7, 16, 0);
        validatePair(36, 64, 1);
        validatePair(100, 140, 2);
        validatePair(149, 165, 3);
        validatePair(183, 196, 4);
    }

    private void validatePair(int expectedFirst, int expectedSecond, int index) {
        Pair<WiFiChannel, WiFiChannel> pair = fixture.getWiFiChannelPairs().get(index);
        assertEquals(expectedFirst, pair.first.getChannel());
        assertEquals(expectedSecond, pair.second.getChannel());
    }

    @Test
    public void testGetAvailableChannels() throws Exception {
        assertEquals(24, fixture.getAvailableChannels(Locale.US).size());
        assertEquals(16, fixture.getAvailableChannels(Locale.UK).size());
        assertEquals(19, fixture.getAvailableChannels(Locale.JAPAN).size());
    }
}