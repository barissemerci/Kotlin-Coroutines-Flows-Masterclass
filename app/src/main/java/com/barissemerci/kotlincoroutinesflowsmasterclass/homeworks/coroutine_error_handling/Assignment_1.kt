package com.barissemerci.kotlincoroutinesflowsmasterclass.homeworks.coroutine_error_handling
/*
[Medium] Assignment #1
You want to send out a weekly newsletter to your friends and family
where you cover some interesting topics about a specific bird each week.
You setup a small program that takes in a list of email addresses
and then sends the newsletter to each of them. You decide to use coroutines to send emails asynchronously,
but you’ve encountered a bug.
Instructions
The program includes a check to see if the email is valid before sending out the email.
If it is not valid, it throws an exception.
Fix the program so that an email is sent to all valid email addresses regardless of
whether there are some invalid ones in the list.
 */
