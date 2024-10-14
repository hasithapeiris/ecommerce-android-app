package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.models.NotificationData
import com.example.ecommerceapp.services.NotificationService

class NotificationsAdapter(
    private val token: String,
    private val notificationService: NotificationService
) : RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder>() {

    private var notifications: MutableList<NotificationData> = mutableListOf()

    // Function to set notifications list
    fun setNotifications(notifications: List<NotificationData>) {
        this.notifications = notifications.toMutableList()
        notifyDataSetChanged()
    }
    //oncreate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount(): Int = notifications.size

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val messageTextView: TextView = itemView.findViewById(R.id.textViewMessage)
        private val radioButton: RadioButton = itemView.findViewById(R.id.radioButton3)

        fun bind(notification: NotificationData) {
            titleTextView.text = notification.title
            messageTextView.text = notification.message
            radioButton.isChecked = notification.isRead

            radioButton.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    if (!notification.isRead) {
                        markAsRead(notification.id, position)
                    }
                }
            }
        }

        private fun markAsRead(notificationId: String, position: Int) {
            notificationService.markNotificationAsRead("Bearer $token", notificationId) { success ->
                if (success) {
                    // Update the notification in the list as 'read'
                    notifications[position] = notifications[position].copy(isRead = true)
                    notifyItemChanged(position)
                } else {
                    // Handle the failure case, show an error message if needed
                }
            }
        }
    }
}
