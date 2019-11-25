/**
 * Investigate using Streams in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 15]
 * Author: Andrew Jarombek
 * Date: 11/24/2019
 */

using System.IO;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace streams_test
{
    [TestClass]
    public class StreamsTest
    {
        /// <summary>
        /// Prove that the current FileStream can write, read, and seek, but not timeout.
        /// Network streams support timeouts, file and memory streams do not.
        /// </summary>
        [TestMethod]
        public void FileStreamReadWriteSeek()
        {
            using (Stream stream = new FileStream("test.json", FileMode.Create))
            {
                Assert.IsTrue(stream.CanWrite);
                Assert.IsTrue(stream.CanRead);
                Assert.IsTrue(stream.CanSeek);
                Assert.IsFalse(stream.CanTimeout);
            }
        }

        /// <summary>
        /// Prove that a FileStream can write bytes and then and then read them back into a string. 
        /// </summary>
        [TestMethod]
        public async Task FileStreamWritesAndReadsBytes()
        {
            using (Stream stream = new FileStream("test.json", FileMode.Create))
            {
                byte[] bytes = Encoding.ASCII.GetBytes("{\"name\":\"andy\"}");
                await stream.WriteAsync(bytes, 0, bytes.Length);
                
                Assert.AreEqual(15, stream.Length);
                Assert.AreEqual(15, stream.Position);

                stream.Position = 0;
                Assert.AreEqual(0, stream.Position);

                byte[] newBytes = new byte[bytes.Length];
                await stream.ReadAsync(newBytes, 0, newBytes.Length);

                string json = Encoding.ASCII.GetString(newBytes);
                Assert.AreEqual("{\"name\":\"andy\"}", json);
            }
        }

        /// <summary>
        /// Prove that a FileStream can write and read single bytes at a time.
        /// </summary>
        [TestMethod]
        public void FileStreamWriteAndReadByte()
        {
            using (Stream stream = new FileStream("test.json", FileMode.Create))
            {
                byte openBrace = Encoding.ASCII.GetBytes("{")[0];
                byte closeBrace = Encoding.ASCII.GetBytes("}")[0];
                
                stream.WriteByte(openBrace);
                Assert.AreEqual(1, stream.Length);
                Assert.AreEqual(1, stream.Position);
                
                stream.WriteByte(closeBrace);
                Assert.AreEqual(2, stream.Length);
                Assert.AreEqual(2, stream.Position);

                stream.Position = 0;

                byte firstByte = (byte) stream.ReadByte();
                Assert.AreEqual("{", Encoding.ASCII.GetString(new [] {firstByte}));
                
                byte secondByte = (byte) stream.ReadByte();
                Assert.AreEqual("}", Encoding.ASCII.GetString(new [] {secondByte}));
            }
        }

        /// <summary>
        /// Prove that layering a buffered stream on top of a file stream causes the buffer in the buffered
        /// stream to read ahead in the file stream.
        /// </summary>
        [TestMethod]
        public void BufferedStreamReadsAhead()
        {
            using (FileStream fs = File.OpenRead("../../../sample.json"))
            using (BufferedStream bs = new BufferedStream(fs, 20))
            {
                // Since the buffer size is 20, the BufferedStream will read ahead 20 positions,
                // placing those 20 bytes in the read-ahead buffer.
                byte firstByte = (byte) bs.ReadByte();
                Assert.AreEqual("{", Encoding.ASCII.GetString(new [] {firstByte}));
                
                Assert.AreEqual(20, fs.Position);
                Assert.AreEqual(1, bs.Position);
            }
        }
    }
}