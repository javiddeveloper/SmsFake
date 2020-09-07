//
//
//
////package com.sattar.j.smsfake.view.navigations.sendMessage.adapter
////
//import android.R
//import android.app.Activity
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.Filter
//import android.widget.TextView
//import androidx.annotation.NonNull
//import androidx.annotation.Nullable
//import com.sattar.j.smsfake.data.entity.Destination
//import com.sattar.j.smsfake.tools.customViews.CustomImageView
////
////
///**
// * @author  : javid
// * @since   : 2020/Sep -- 11:33 AM
// * @summary : --
// */
//class DestinationAdapter(context: Context?, viewResourceId: Int, items: ArrayList<Destination>) : ArrayAdapter<Destination?>(context, viewResourceId, items) {
//    private val MY_DEBUG_TAG = "CustomerAdapter"
//    private val items: ArrayList<Destination>
//    private val itemsAll: ArrayList<Destination>
//    private val suggestions: ArrayList<Destination>
//    private val viewResourceId: Int
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var v: View? = convertView
//        if (v == null) {
//            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            v = vi.inflate(viewResourceId, null)
//        }
//        val customer: Destination = items[position]
//        if (customer != null) {
//            val customerNameLabel = v?.findViewById(R.id.txt_name) as TextView
//            customerNameLabel.setText(customer.getName())
//        }
//        return v
//    }
//
//    override fun getFilter(): Filter {
//        return nameFilter
//    }
//
//    var nameFilter: Filter = object : Filter() {
//       override fun convertResultToString(resultValue: Any): String {
//            return (resultValue as Destination).getName()
//        }
//
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            return if (constraint != null) {
//                suggestions.clear()
//                for (customer in itemsAll) {
//                    if (customer.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
//                        suggestions.add(customer)
//                    }
//                }
//                val filterResults = FilterResults()
//                filterResults.values = suggestions
//                filterResults.count = suggestions.size()
//                filterResults
//            } else {
//                FilterResults()
//            }
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            if (results != null && results.count > 0) {
//                clear()
//                for (c in results.values) {
//                    add(c)
//                }
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    init {
//        this.items = items
//        itemsAll = items.clone() as ArrayList<Destination>
//        suggestions = ArrayList<Destination>()
//        this.viewResourceId = viewResourceId
//    }
//}
////class DestinationAdapter(context: Context, resourceId: Int, items: ArrayList<Destination>) : ArrayAdapter<Destination>(context, resourceId, items) {
////
////    //    private val context: Context
////    private val resourceId: Int
////    val items: MutableList<Destination>
////    private val tempItems: MutableList<Destination>
////    private val suggestions: MutableList<Destination>
////
////    init {
////        this.items = items
////        this.resourceId = resourceId
////        tempItems = ArrayList(items)
////        suggestions = ArrayList()
////    }
////
////    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
////        var view: View? = convertView
////        try {
////            if (convertView == null) {
////                val inflater = (context as Activity).layoutInflater
////                view = inflater.inflate(resourceId, parent, false)
////            }
////            val destination: Destination = getItem(position)
////            val name        = view?.findViewById(R.id.txt_name) as TextView
////            val number      = view.findViewById(R.id.txt_number) as TextView
////            val imageView   = view.findViewById(R.id.img_avatar) as CustomImageView
//////            val name: TextView?             = view?.findViewById(R.id.txt_name)
//////            val number: TextView?           = view?.findViewById(R.id.txt_number)
//////            val imageView: CustomImageView? = view?.findViewById(R.id.img_avatar)
////            imageView.setImageCircle(destination.avatar)
////            name.text = destination.name
////            number.text = destination.number
////        } catch (e: Exception) {
////            e.printStackTrace()
////        }
////        return view!!
////    }
////
////    fun addItems(newItems: List<Destination>) {
////        items.addAll(newItems)
////        notifyDataSetChanged()
////    }
////
////    @Nullable
////    override fun getItem(position: Int): Destination {
////        return items[position]
////    }
////
////    override fun getCount(): Int {
////        return items.size
////    }
////
////    override fun getItemId(position: Int): Long {
////        return position.toLong()
////    }
////
////    @NonNull
////    override fun getFilter(): Filter {
////        return fruitFilter
////    }
////
////    private val fruitFilter: Filter = object : Filter() {
////        override fun convertResultToString(resultValue: Any): CharSequence {
////            val fruit: Destination = resultValue as Destination
////            return fruit.name
////        }
////
////        override fun performFiltering(charSequence: CharSequence?): FilterResults {
////            return if (charSequence != null) {
////                suggestions.clear()
////                for (fruit in tempItems) {
////                    if (fruit.name.toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
////                        suggestions.add(fruit)
////                    }
////                }
////                val filterResults = FilterResults()
////                filterResults.values = suggestions
////                filterResults.count = suggestions.size
////                filterResults
////            } else {
////                FilterResults()
////            }
////        }
////
////        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
////            if (filterResults != null && filterResults.count > 0) {
////                clear()
////                for (fruitObj in filterResults.values as List<Destination>) {
////                    add(fruitObj)
////                }
////                notifyDataSetChanged()
////            } else {
////                clear()
////                notifyDataSetChanged()
////            }
////        }
////    }
////
////}