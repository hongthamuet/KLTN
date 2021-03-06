///////////////////////////////////////////////////////////////////////////////
//
// This file was automatically generated by RANOREX.
// DO NOT MODIFY THIS FILE! It is regenerated by the designer.
// All your modifications will be lost!
// http://www.ranorex.com
//
///////////////////////////////////////////////////////////////////////////////

using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using System.Drawing;
using System.Threading;
using WinForms = System.Windows.Forms;

using Ranorex;
using Ranorex.Core;
using Ranorex.Core.Testing;
using Ranorex.Core.Repository;

namespace ZINGID
{
#pragma warning disable 0436 //(CS0436) The type 'type' in 'assembly' conflicts with the imported type 'type2' in 'assembly'. Using the type defined in 'assembly'.
    /// <summary>
    ///The Recording2 recording.
    /// </summary>
    [TestModule("74f6fda8-3144-4384-8152-d01370b37551", ModuleType.Recording, 1)]
    public partial class Recording2 : ITestModule
    {
        /// <summary>
        /// Holds an instance of the ZINGIDRepository repository.
        /// </summary>
        public static ZINGIDRepository repo = ZINGIDRepository.Instance;

        static Recording2 instance = new Recording2();

        /// <summary>
        /// Constructs a new instance.
        /// </summary>
        public Recording2()
        {
        }

        /// <summary>
        /// Gets a static instance of this recording.
        /// </summary>
        public static Recording2 Instance
        {
            get { return instance; }
        }

#region Variables

#endregion

        /// <summary>
        /// Starts the replay of the static recording <see cref="Instance"/>.
        /// </summary>
        [System.CodeDom.Compiler.GeneratedCode("Ranorex", global::Ranorex.Core.Constants.CodeGenVersion)]
        public static void Start()
        {
            TestModuleRunner.Run(Instance);
        }

        /// <summary>
        /// Performs the playback of actions in this recording.
        /// </summary>
        /// <remarks>You should not call this method directly, instead pass the module
        /// instance to the <see cref="TestModuleRunner.Run(ITestModule)"/> method
        /// that will in turn invoke this method.</remarks>
        [System.CodeDom.Compiler.GeneratedCode("Ranorex", global::Ranorex.Core.Constants.CodeGenVersion)]
        void ITestModule.Run()
        {
            Mouse.DefaultMoveTime = 300;
            Keyboard.DefaultKeyPressTime = 20;
            Delay.SpeedFactor = 1.00;

            Init();

            Report.Log(ReportLevel.Info, "Website", "Opening web site 'https://id.zing.vn/v2/login?apikey=92140c0e46c54994812403f564787c14&pid=38&next=https%3A%2F%2Fid.zing.vn%2Fv2%2Finfosetting%3Fapikey%3D92140c0e46c54994812403f564787c14%26pid%3D38#' with browser 'Edge' in normal mode.", new RecordItemIndex(0));
            Host.Current.OpenBrowser("https://id.zing.vn/v2/login?apikey=92140c0e46c54994812403f564787c14&pid=38&next=https%3A%2F%2Fid.zing.vn%2Fv2%2Finfosetting%3Fapikey%3D92140c0e46c54994812403f564787c14%26pid%3D38#", "Edge", "", false, false, false, false, false, true);
            Delay.Milliseconds(0);
            
            Report.Log(ReportLevel.Info, "Mouse", "Mouse Left Click item 'ApplicationUnderTest.LoginAccount' at 52;4.", repo.ApplicationUnderTest.LoginAccountInfo, new RecordItemIndex(1));
            repo.ApplicationUnderTest.LoginAccount.Click("52;4");
            Delay.Milliseconds(0);
            
            Report.Log(ReportLevel.Info, "Keyboard", "Key sequence 'hongthame' with focus on 'ApplicationUnderTest.LoginAccount'.", repo.ApplicationUnderTest.LoginAccountInfo, new RecordItemIndex(2));
            repo.ApplicationUnderTest.LoginAccount.PressKeys("hongthame");
            Delay.Milliseconds(0);
            
            Report.Log(ReportLevel.Info, "Mouse", "Mouse Left Click item 'ApplicationUnderTest.LoginPwd' at 102;11.", repo.ApplicationUnderTest.LoginPwdInfo, new RecordItemIndex(3));
            repo.ApplicationUnderTest.LoginPwd.Click("102;11");
            Delay.Milliseconds(0);
            
            Report.Log(ReportLevel.Info, "Keyboard", "Key sequence '274ygedge23' with focus on 'ApplicationUnderTest.LoginPwd'.", repo.ApplicationUnderTest.LoginPwdInfo, new RecordItemIndex(4));
            repo.ApplicationUnderTest.LoginPwd.PressKeys("274ygedge23");
            Delay.Milliseconds(0);
            
            Report.Log(ReportLevel.Info, "Mouse", "Mouse Left Click item 'ApplicationUnderTest.Submit' at 48;16.", repo.ApplicationUnderTest.SubmitInfo, new RecordItemIndex(5));
            repo.ApplicationUnderTest.Submit.Click("48;16");
            Delay.Milliseconds(0);
            
        }

#region Image Feature Data
#endregion
    }
#pragma warning restore 0436
}
