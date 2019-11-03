/**
 * Learn how to use Asynchronous Functions in the .NET Framework and C#
 * Sources: [C# 7.0 In a Nutshell: Page 590-]
 * Author: Andrew Jarombek
 * Date: 10/27/2019
 */

using System;
using System.Threading.Tasks;
using static System.Diagnostics.Debug;

namespace concurrency
{
    public static class Async
    {
        public static async void Execute()
        {
            // Just as JavaScript has async/await keywords to avoid using callback functions within promises, C#
            // has async/await keywords to avoid using GetAwaiter() and OnCompleted() on Tasks.
            var mockApiResponse = await MockApiCall();
            Assert(mockApiResponse == "API response body");

            // The 'await' keyword can appear in any context that a function can be called.  For example, 'await'
            // can be used when printing out a value:
            Console.WriteLine(await MockApiCall());
            
            // You can get progress reports back from asynchronous method calls using the IProgress interface and its
            // concrete class Progress.
            var progressReport = new Progress<int>(percent => Console.WriteLine($"{percent}%"));
            string catPicture = await DownloadCatPicture(progressReport);
            Assert(catPicture == "russianBlueCat.jpg");
            Console.WriteLine(catPicture);
        }

        /// <summary>
        /// Mock an API call to demonstrate how the async/await keywords work.
        /// </summary>
        /// <returns>A string representing the response body from an API.</returns>
        private static Task<string> MockApiCall()
        {
            return Task.Run(() =>
            {
                var task = Task.Delay(100);
                task.Wait();
                return "API response body";
            });
        }

        /// <summary>
        /// Mock a image download of a cat picture.
        /// </summary>
        /// <param name="onProgressPercentChanged"></param>
        /// <returns></returns>
        private static Task<string> DownloadCatPicture(IProgress<int> onProgressPercentChanged)
        {
            for (int i = 1; i <= 100; i++)
            {
                var task = Task.Delay(100);
                task.Wait();
                onProgressPercentChanged.Report(i);
            }

            return Task.Run(() => "russianBlueCat.jpg");
        }
    }
}

// I don't wish my feelings were any different.  You are your wonderful self.