/**
 * Investigate operating system interaction in the .NET Framework
 * Sources: [C# 7.0 In a Nutshell: Page 298-]
 * Author: Andrew Jarombek
 * Date: 7/29/2019
 */

using System;
using System.Diagnostics;
using static System.Diagnostics.Debug;

namespace BasicFrameworkTypes
{
    public static class OS
    {
        public static void Execute()
        {
            // Check the version of the current operating system.
            var os = Environment.OSVersion;
            
            // I'm running these commands on my Windows 10 desktop.
            Assert(os.Platform.ToString() == "Win32NT");
            Assert(os.Version.Major == 6);

            // If the OS platform is Windows, start the cmd command line.  If it's MacOS, start bash.
            if (os.Platform == PlatformID.Win32NT)
            {
                var cmd = new Process();
                cmd.StartInfo.FileName = "cmd.exe";
                cmd.Start();
                cmd.WaitForExit();
            }
            else if (os.Platform == PlatformID.MacOSX)
            {
                var bash = new Process();
                bash.StartInfo.FileName = "/bin/bash";
                bash.Start();
                bash.WaitForExit();
            }
        }
    }
}