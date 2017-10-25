/*
 *  BIP32 library, a Java implementation of BIP32
 *  Copyright (C) 2017 Alan Evans, NovaCrypto
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Original source: https://github.com/NovaCrypto/BIP32
 *  You can contact the authors via github issues.
 */

package io.github.novacrypto.bip32;

import io.github.novacrypto.bip32.networks.Bitcoin;
import io.github.novacrypto.bip39.SeedCalculator;
import org.junit.Ignore;
import org.junit.Test;

import static io.github.novacrypto.base58.Base58.base58Encode;
import static org.junit.Assert.assertEquals;

public final class DeriveNonHardenedTests {

    @Test
    @Ignore
    public void deriveFirstIndexNonHardened() {
        assertPrivateKey("xprv9vUtFfdFpb4T59CoQMSLmbpVg1dVZcWXsznR8BVeV4gn7pN1dZa7Kq1VR7fovgbbodEziyyk1i9wrb8wmfwr69DsGsdgV24EtDh5XgzqjHD",
                "edge talent poet tortoise trumpet dose", "m/0", Bitcoin.MAIN_NET);
    }

    private void assertPrivateKey(String expectedBip32Root, String mnemonic, String derivationPath, Network network) {
        final byte[] seed = new SeedCalculator().calculateSeed(mnemonic, "");

        final byte[] bip32Root = findPrivateKey(seed, network, derivationPath);
        final String actualBip32Root = base58Encode(bip32Root).toString();
        assertEquals(expectedBip32Root, actualBip32Root);
    }

    private byte[] findPrivateKey(byte[] seed, Network network, String derivationPath) {
        return PrivateRoot.fromSeed(seed, network)
                .cKDpriv(0)
                .toByteArray();
    }
}
