
package com.plcoding.coroutinesmasterclass.sections.coroutine_cancellation.homework.assignmenttwo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/*
[Medium] Assignment #2
You’ve been feeling charitable recently and decided to create a small application to simulate transferring a donation to a local animal charity.
You also ensure to include a cleanup function to run after each transfer request, regardless of whether it was successful, failed, or canceled.
Upon completion, you encounter some unexpected behaviors.
When you cancel the transfer after money is taken from your account, the money is lost, and the charity never receives it.
Resources aren’t properly cleaned up when cancelling a transfer
Instructions
Find the bugs and fix them.
Once money has been taken from your account, it has to be added to the charity’s account.
The application should always clean up the resources, whether the transfer succeeded, failed or was cancelled.
Properly handle any CancellationExceptions
*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoneyTransferScreen(
    state: MoneyTransferState,
    onAction: (MoneyTransferAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val primaryGreen = Color(0xFF4CAF50)
    val secondaryGreen = Color(0xFF81C784)
    val background = Color(0xFFE8F5E9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Savings: $${String.format("%.2f", state.savingsBalance)}",
            color = primaryGreen,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Checking: $${String.format("%.2f", state.checkingBalance)}",
            color = primaryGreen,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField2(
            state = state.transferAmount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            enabled = !state.isTransferring,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(16.dp),
            decorator = { innerBox ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (state.transferAmount.text.isEmpty()) {
                            Text(
                                text = "Enter amount",
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                    alpha = 0.4f
                                )
                            )
                        }
                        innerBox()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                onAction((MoneyTransferAction.TransferFunds))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = secondaryGreen
            ),
            modifier = Modifier
                .height(IntrinsicSize.Min)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(15.dp)
                        .alpha(if (state.isTransferring) 1f else 0f),
                    strokeWidth = 1.5.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Transfer Funds",
                    color = Color.White,
                    modifier = Modifier
                        .alpha(if (state.isTransferring) 0f else 1f)
                )
            }
        }
        if (state.isTransferring) {
            Button(
                onClick = {
                    onAction(MoneyTransferAction.CancelTransfer)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = secondaryGreen
                ),
            ) {
                Text(text = "Cancel Transfer")
            }
        }
        val processingStateText = when (state.processingState) {
            ProcessingState.CheckingFunds -> {
                "Checking if there is enough funds..."
            }
            ProcessingState.CreditingAccount -> {
                "Depositing money to checking account..."
            }
            ProcessingState.DebitingAccount -> {
                "Withdrawing money from savings account..."
            }
            ProcessingState.CleanupResources -> {
                "Cleaning up resources used..."
            }
            else -> null
        }

        if (processingStateText != null) {
            Text(text = processingStateText)
        }
        if (state.resultMessage != null) {
            Text(text = state.resultMessage)
        }
    }
}
