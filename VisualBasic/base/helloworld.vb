' Hello World in Visual Basic for Copying "Hello World" to the clipboard
' Sources: [https://bit.ly/2lN3TwO, https://bit.ly/2lQRIiq]
' Author: Andrew Jarombek
' Date: 9/12/2019

Sub Copy_To_Clipboard()
    ' DataObject is in the Microsoft Forms 2.0 library
    Dim obj As New DataObject
    Dim text As String
   
    txt = "Hello World"
    obj.setText txt
   
    obj.PutInClipboard
    MsgBox "'Hello World' Copied to Clipboard", vbInformation
   
End Sub