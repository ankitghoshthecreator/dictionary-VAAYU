package com.ankitghoshthecreator.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankitghoshthecreator.dictionary.databinding.MeaningRecyclerRowBinding

class MeaningAdapter(private var meaningList : List<Meaning>): RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {
    class MeaningViewHolder(private val binding: MeaningRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning){
            //Bind all meaning
            binding.partsOfSpeechTextv.text= meaning.partOfSpeech
            binding.definitionTv.text=meaning.definitions.joinToString("\n\n"){
                var currentIndex= meaning.definitions.indexOf(it)
                (currentIndex+1).toString()+" "+ it.definition.toString()
            }

            if (meaning.synonyms.isEmpty()){
                binding.synonymsTitleTv.visibility= View.GONE
                binding.synonymsTv.visibility= View.GONE
            }else{
                binding.synonymsTitleTv.visibility= View.VISIBLE
                binding.synonymsTv.visibility= View.VISIBLE
                binding.synonymsTv.text= meaning.synonyms.joinToString (", ")
            }
            if (meaning.antonyms.isEmpty()){
                binding.antonymsTv.visibility= View.GONE
                binding.antonymsTitleTv.visibility= View.GONE
            }else{
                binding.antonymsTv.visibility= View.VISIBLE
                binding.antonymsTitleTv.visibility= View.VISIBLE
                binding.antonymsTv.text= meaning.antonyms.joinToString (", ")
            }
        }
    }

    fun updateNewData(newMeaningList: List<Meaning>){
        meaningList=newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = MeaningRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}