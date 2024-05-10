import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barrettsbudgettracker.Adapter
import com.example.barrettsbudgettracker.R
import com.example.barrettsbudgettracker.Transaction
import com.example.barrettsbudgettracker.TransactionActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var transaction: ArrayList<Transaction>
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var balanceTextView: TextView
    private lateinit var budgetTextView: TextView
    private lateinit var expenseTextView: TextView
    private lateinit var addBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views from XML
        recyclerView = findViewById(R.id.recyclerView)
        balanceTextView = findViewById(R.id.balance)
        budgetTextView = findViewById(R.id.budget)
        expenseTextView = findViewById(R.id.expenses)
        addBtn = findViewById(R.id.addBtn) // Initialize addBtn

        transaction = arrayListOf(
            Transaction("Weekly budget", 500.00),
            Transaction("Coffee", -20.00),
            Transaction("Gas", -45.00),
            Transaction("Parking", -60.00),
            Transaction("Lunch", -80.00),
            Transaction("Snacks", -10.00),
            Transaction("Art supplies", -30.00),
            Transaction("Clothes", -50.00),
            Transaction("Date", -50.00)
        )

        adapter = Adapter(transaction)
        recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        updateDashboard()

        addBtn.setOnClickListener{
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateDashboard() {
        val totalAmount = transaction.map { it.amount }.sum()
        val budgetAmount = transaction.filter { it.amount > 0 }.map { it.amount }.sum()
        val expenseAmount = totalAmount - budgetAmount

        balanceTextView.text = "$%.2f".format(totalAmount)
        budgetTextView.text = "$%.2f".format(budgetAmount)
        expenseTextView.text = "$%.2f".format(expenseAmount)
    }
}

