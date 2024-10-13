package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.NotificationsAdapter
import com.example.ecommerceapp.models.NotificationData
import com.example.ecommerceapp.services.NotificationService

class NotificationsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var notificationService: NotificationService
    private lateinit var notificationsAdapter: NotificationsAdapter
    private var token: String? = null // Assume you'll retrieve the token from SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationService = NotificationService()
        // Retrieve token from SharedPreferences or arguments
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewNotifications)
        recyclerView.layoutManager = LinearLayoutManager(context)
        notificationsAdapter = NotificationsAdapter()
        recyclerView.adapter = notificationsAdapter
        fetchNotifications()
        return view
    }

    private fun fetchNotifications() {
        token?.let { validToken ->
            notificationService.getNotifications(validToken) { success, notifications ->
                if (success && notifications != null) {
                    notificationsAdapter.setNotifications(notifications)
                } else {
                    // Handle error
                }
            }
        }
    }
}
