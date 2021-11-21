package com.igeeksky.xtool.core.security;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Patrick.Lau
 * @since 1.0.1 2021-11-20
 */
public class DigestUtilsTest {

    @Test
    public void md5() {
        String text = "Less is more";
        String hex = DigestUtils.md5(text);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
    }

    @Test
    public void testMd5() {
        String text = "Less is more";
        String hex = DigestUtils.md5(text, StandardCharsets.UTF_8);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
    }

    @Test
    public void testMd51() {
        String text = "Less is more";
        String hex = DigestUtils.md5(text, true);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
    }

    @Test
    public void testMd52() {
        String text = "Less is more";
        String hex = DigestUtils.md5(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
    }

    @Test
    public void testMd53() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.md5(bytes, true);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
    }

    @Test
    public void testMd54() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.md5(bytes, false);
        Assert.assertEquals("DF6AE335A4F5CF721002EAA9299F4A9D", hex);
    }

    @Test
    public void testMd55() {
        String text = "Less is more";
        String hex = DigestUtils.md5(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("DF6AE335A4F5CF721002EAA9299F4A9D", hex);
    }

    @Test
    public void testMd56() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.md5(bytes);
        Assert.assertEquals("df6ae335a4f5cf721002eaa9299f4a9d", hex);
    }

    @Test
    public void sha1() {
        String text = "Less is more";
        String hex = DigestUtils.sha1(text);
        Assert.assertEquals("a475f870d8704061d5cabd96af0ecb0d6d4073bb", hex);
    }

    @Test
    public void testSha1() {
        String text = "Less is more";
        String hex = DigestUtils.sha1(text, StandardCharsets.UTF_8);
        Assert.assertEquals("a475f870d8704061d5cabd96af0ecb0d6d4073bb", hex);
    }

    @Test
    public void testSha11() {
        String text = "Less is more";
        String hex = DigestUtils.sha1(text, true);
        Assert.assertEquals("a475f870d8704061d5cabd96af0ecb0d6d4073bb", hex);
    }

    @Test
    public void testSha12() {
        String text = "Less is more";
        String hex = DigestUtils.sha1(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("a475f870d8704061d5cabd96af0ecb0d6d4073bb", hex);
    }

    @Test
    public void testSha13() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha1(bytes, true);
        Assert.assertEquals("a475f870d8704061d5cabd96af0ecb0d6d4073bb", hex);
    }

    @Test
    public void testSha14() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha1(bytes, false);
        Assert.assertEquals("A475F870D8704061D5CABD96AF0ECB0D6D4073BB", hex);
    }

    @Test
    public void testSha15() {
        String text = "Less is more";
        String hex = DigestUtils.sha1(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("A475F870D8704061D5CABD96AF0ECB0D6D4073BB", hex);
    }

    @Test
    public void testSha16() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha1(bytes);
        Assert.assertEquals("a475f870d8704061d5cabd96af0ecb0d6d4073bb", hex);
    }

    @Test
    public void sha224() {
        String text = "Less is more";
        String hex = DigestUtils.sha224(text);
        Assert.assertEquals("97fd5490c2f95ab7889d934eeeec92c143aa25512b1d78d2db888095", hex);
    }

    @Test
    public void testSha224() {
        String text = "Less is more";
        String hex = DigestUtils.sha224(text, StandardCharsets.UTF_8);
        Assert.assertEquals("97fd5490c2f95ab7889d934eeeec92c143aa25512b1d78d2db888095", hex);
    }

    @Test
    public void testSha2241() {
        String text = "Less is more";
        String hex = DigestUtils.sha224(text, true);
        Assert.assertEquals("97fd5490c2f95ab7889d934eeeec92c143aa25512b1d78d2db888095", hex);
    }

    @Test
    public void testSha2242() {
        String text = "Less is more";
        String hex = DigestUtils.sha224(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("97fd5490c2f95ab7889d934eeeec92c143aa25512b1d78d2db888095", hex);
    }

    @Test
    public void testSha2243() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha224(bytes, true);
        Assert.assertEquals("97fd5490c2f95ab7889d934eeeec92c143aa25512b1d78d2db888095", hex);
    }

    @Test
    public void testSha2244() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha224(bytes, false);
        Assert.assertEquals("97FD5490C2F95AB7889D934EEEEC92C143AA25512B1D78D2DB888095", hex);
    }

    @Test
    public void testSha2245() {
        String text = "Less is more";
        String hex = DigestUtils.sha224(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("97FD5490C2F95AB7889D934EEEEC92C143AA25512B1D78D2DB888095", hex);
    }

    @Test
    public void testSha2246() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha224(bytes);
        Assert.assertEquals("97fd5490c2f95ab7889d934eeeec92c143aa25512b1d78d2db888095", hex);
    }

    @Test
    public void sha256() {
        String text = "Less is more";
        String hex = DigestUtils.sha256(text);
        Assert.assertEquals("95493d6610204635b064cfa67facb85ac161f79b7fe6f55e578fa83cc068d3d0", hex);
    }

    @Test
    public void testSha256() {
        String text = "Less is more";
        String hex = DigestUtils.sha256(text, StandardCharsets.UTF_8);
        Assert.assertEquals("95493d6610204635b064cfa67facb85ac161f79b7fe6f55e578fa83cc068d3d0", hex);
    }

    @Test
    public void testSha2561() {
        String text = "Less is more";
        String hex = DigestUtils.sha256(text, true);
        Assert.assertEquals("95493d6610204635b064cfa67facb85ac161f79b7fe6f55e578fa83cc068d3d0", hex);
    }

    @Test
    public void testSha2562() {
        String text = "Less is more";
        String hex = DigestUtils.sha256(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("95493d6610204635b064cfa67facb85ac161f79b7fe6f55e578fa83cc068d3d0", hex);
    }

    @Test
    public void testSha2563() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha256(bytes, true);
        Assert.assertEquals("95493d6610204635b064cfa67facb85ac161f79b7fe6f55e578fa83cc068d3d0", hex);
    }

    @Test
    public void testSha2564() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha256(bytes, false);
        Assert.assertEquals("95493D6610204635B064CFA67FACB85AC161F79B7FE6F55E578FA83CC068D3D0", hex);
    }

    @Test
    public void testSha2565() {
        String text = "Less is more";
        String hex = DigestUtils.sha256(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("95493D6610204635B064CFA67FACB85AC161F79B7FE6F55E578FA83CC068D3D0", hex);
    }

    @Test
    public void testSha2566() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha256(bytes);
        Assert.assertEquals("95493d6610204635b064cfa67facb85ac161f79b7fe6f55e578fa83cc068d3d0", hex);
    }

    @Test
    public void sha384() {
        String text = "Less is more";
        String hex = DigestUtils.sha384(text);
        Assert.assertEquals("1a93d30189c26042cc6880cc7a49989a822caef5168b4a0285890b599de2e7116a2c9f594bb0e97b1a5cb03eb071fe2a", hex);
    }

    @Test
    public void testSha384() {
        String text = "Less is more";
        String hex = DigestUtils.sha384(text, StandardCharsets.UTF_8);
        Assert.assertEquals("1a93d30189c26042cc6880cc7a49989a822caef5168b4a0285890b599de2e7116a2c9f594bb0e97b1a5cb03eb071fe2a", hex);
    }

    @Test
    public void testSha3841() {
        String text = "Less is more";
        String hex = DigestUtils.sha384(text, true);
        Assert.assertEquals("1a93d30189c26042cc6880cc7a49989a822caef5168b4a0285890b599de2e7116a2c9f594bb0e97b1a5cb03eb071fe2a", hex);
    }

    @Test
    public void testSha3842() {
        String text = "Less is more";
        String hex = DigestUtils.sha384(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("1a93d30189c26042cc6880cc7a49989a822caef5168b4a0285890b599de2e7116a2c9f594bb0e97b1a5cb03eb071fe2a", hex);
    }

    @Test
    public void testSha3843() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha384(bytes, true);
        Assert.assertEquals("1a93d30189c26042cc6880cc7a49989a822caef5168b4a0285890b599de2e7116a2c9f594bb0e97b1a5cb03eb071fe2a", hex);
    }

    @Test
    public void testSha3844() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha384(bytes, false);
        Assert.assertEquals("1A93D30189C26042CC6880CC7A49989A822CAEF5168B4A0285890B599DE2E7116A2C9F594BB0E97B1A5CB03EB071FE2A", hex);
    }

    @Test
    public void testSha3845() {
        String text = "Less is more";
        String hex = DigestUtils.sha384(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("1A93D30189C26042CC6880CC7A49989A822CAEF5168B4A0285890B599DE2E7116A2C9F594BB0E97B1A5CB03EB071FE2A", hex);
    }

    @Test
    public void testSha3846() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha384(bytes);
        Assert.assertEquals("1a93d30189c26042cc6880cc7a49989a822caef5168b4a0285890b599de2e7116a2c9f594bb0e97b1a5cb03eb071fe2a", hex);
    }

    @Test
    public void sha512() {
        String text = "Less is more";
        String hex = DigestUtils.sha512(text);
        Assert.assertEquals("bc0c2f107134abcf88bcc68ff21197be5e7a09004cb9b8868a942a9344ac6df8799181449e38ea031f880e536905c1ff20fd1469f196c2e6c6d996cf5110a4c9", hex);
    }

    @Test
    public void testSha512() {
        String text = "Less is more";
        String hex = DigestUtils.sha512(text, StandardCharsets.UTF_8);
        Assert.assertEquals("bc0c2f107134abcf88bcc68ff21197be5e7a09004cb9b8868a942a9344ac6df8799181449e38ea031f880e536905c1ff20fd1469f196c2e6c6d996cf5110a4c9", hex);
    }

    @Test
    public void testSha5121() {
        String text = "Less is more";
        String hex = DigestUtils.sha512(text, true);
        Assert.assertEquals("bc0c2f107134abcf88bcc68ff21197be5e7a09004cb9b8868a942a9344ac6df8799181449e38ea031f880e536905c1ff20fd1469f196c2e6c6d996cf5110a4c9", hex);
    }

    @Test
    public void testSha5122() {
        String text = "Less is more";
        String hex = DigestUtils.sha512(text, StandardCharsets.UTF_8, true);
        Assert.assertEquals("bc0c2f107134abcf88bcc68ff21197be5e7a09004cb9b8868a942a9344ac6df8799181449e38ea031f880e536905c1ff20fd1469f196c2e6c6d996cf5110a4c9", hex);
    }

    @Test
    public void testSha5123() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha512(bytes, true);
        Assert.assertEquals("bc0c2f107134abcf88bcc68ff21197be5e7a09004cb9b8868a942a9344ac6df8799181449e38ea031f880e536905c1ff20fd1469f196c2e6c6d996cf5110a4c9", hex);
    }

    @Test
    public void testSha5124() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha512(bytes, false);
        Assert.assertEquals("BC0C2F107134ABCF88BCC68FF21197BE5E7A09004CB9B8868A942A9344AC6DF8799181449E38EA031F880E536905C1FF20FD1469F196C2E6C6D996CF5110A4C9", hex);
    }

    @Test
    public void testSha5125() {
        String text = "Less is more";
        String hex = DigestUtils.sha512(text, StandardCharsets.UTF_8, false);
        Assert.assertEquals("BC0C2F107134ABCF88BCC68FF21197BE5E7A09004CB9B8868A942A9344AC6DF8799181449E38EA031F880E536905C1FF20FD1469F196C2E6C6D996CF5110A4C9", hex);
    }

    @Test
    public void testSha5126() {
        byte[] bytes = "Less is more".getBytes(StandardCharsets.UTF_8);
        String hex = DigestUtils.sha512(bytes);
        Assert.assertEquals("bc0c2f107134abcf88bcc68ff21197be5e7a09004cb9b8868a942a9344ac6df8799181449e38ea031f880e536905c1ff20fd1469f196c2e6c6d996cf5110a4c9", hex);
    }

    @Test
    public void testSha5127() {
        try {
            DigestUtils.sha512(nullBytes());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalStateException);
        }
    }

    private static byte[] nullBytes() {
        return null;
    }
}