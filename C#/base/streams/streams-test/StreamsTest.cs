/**
 * Investigate using Streams in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Chapter 15]
 * Author: Andrew Jarombek
 * Date: 11/24/2019
 */

using System;
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

        /// <summary>
        /// Test that the StreamReader/StreamWriter operate as expected when writing and reading text from a file.
        /// </summary>
        [TestMethod]
        public async Task StreamReaderWriterOperateAsExpected()
        {
            Assert.IsFalse(File.Exists("note.txt"));
            
            using (FileStream fs = File.Create("note.txt"))
            using(TextWriter writer = new StreamWriter(fs))
            {
                // WriteLine can be performed synchronously and asynchronously.
                await writer.WriteLineAsync("Hi,");
                writer.WriteLine("I hope you are doing well.");
                await writer.WriteLineAsync("-Andy");
            }
            
            Assert.IsTrue(File.Exists("note.txt"));

            using (FileStream fs = File.OpenRead("note.txt"))
            using (TextReader reader = new StreamReader(fs))
            {
                // ReadLine can also be performed synchronously and asynchronously.
                Assert.AreEqual(reader.ReadLine(), "Hi,");
                Assert.AreEqual(await reader.ReadLineAsync(), "I hope you are doing well.");
                Assert.AreEqual(reader.ReadLine(), "-Andy");
            }
            
            File.Delete("note.txt");
            Assert.IsFalse(File.Exists("note.txt"));
        }

        /// <summary>
        /// Prove that the bytes in a file that is UTF-8 encoded are as expected.
        /// </summary>
        [TestMethod]
        public void StreamUtf8AsExpected()
        {
            using (TextWriter writer = File.CreateText("hi.txt"))
                writer.WriteLine("hi");

            byte[] fileBytes = File.ReadAllBytes("hi.txt");
            Assert.AreEqual(4, fileBytes.Length);
            Assert.AreEqual('h', (char) fileBytes[0]);
            Assert.AreEqual('i', (char) fileBytes[1]);
            
            // At the end of the file comes a carriage return...
            Assert.AreEqual('\r', (char) fileBytes[2]);
            // ...followed by a line feed.
            Assert.AreEqual('\n', (char) fileBytes[3]);
            
            File.Delete("hi.txt");
            Assert.IsFalse(File.Exists("hi.txt"));
        }
        
        /// <summary>
        /// Prove that the bytes in a file that is UTF-16 encoded are as expected.
        /// </summary>
        [TestMethod]
        public void StreamUtf16AsExpected()
        {
            using (Stream stream = File.Create("hi.txt"))
            using (TextWriter writer = new StreamWriter(stream, Encoding.Unicode))
                writer.WriteLine("hi");
            
            byte[] fileBytes = File.ReadAllBytes("hi.txt");
            Assert.AreEqual(10, fileBytes.Length);
            
            // UTF-16 Byte Order Mark (BOM)
            Assert.AreEqual((byte) 255, fileBytes[0]);
            Assert.AreEqual((byte) 254, fileBytes[1]);
            Assert.AreEqual('\uFEFF', BitConverter.ToChar(new [] {fileBytes[0], fileBytes[1]}));
            
            // 'h'
            Assert.AreEqual((byte) 104, fileBytes[2]);
            Assert.AreEqual((byte) 0, fileBytes[3]);
            Assert.AreEqual('h', BitConverter.ToChar(new [] {fileBytes[2], fileBytes[3]}));
            
            // 'i'
            Assert.AreEqual((byte) 105, fileBytes[4]);
            Assert.AreEqual((byte) 0, fileBytes[5]);
            Assert.AreEqual('i', BitConverter.ToChar(new [] {fileBytes[4], fileBytes[5]}));
            
            // Carriage Return <CR>
            Assert.AreEqual((byte) 13, fileBytes[6]);
            Assert.AreEqual((byte) 0, fileBytes[7]);
            Assert.AreEqual('\r', BitConverter.ToChar(new [] {fileBytes[6], fileBytes[7]}));
            
            // Line Feed <LF>
            Assert.AreEqual((byte) 10, fileBytes[8]);
            Assert.AreEqual((byte) 0, fileBytes[9]);
            Assert.AreEqual('\n', BitConverter.ToChar(new [] {fileBytes[8], fileBytes[9]}));
            
            File.Delete("hi.txt");
            Assert.IsFalse(File.Exists("hi.txt"));
        }
    }
}