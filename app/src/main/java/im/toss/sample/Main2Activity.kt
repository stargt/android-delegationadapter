package im.toss.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import im.toss.delegationadapter.ListItemDelegationAdapter
import im.toss.sample.model.*
import java.util.*

/**
 * Kotlin Example
 */
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_simple_main)

        (findViewById(R.id.recyclerView) as RecyclerView).apply {

            layoutManager = LinearLayoutManager(context)

            adapter = ListItemDelegationAdapter<DisplayableItem>().apply {

                addDelegate(createDelegateBuilder(Advertisement::class.java)
                        .layout(R.layout.item_advertisement)
                        .build())

                addDelegate(createDelegateBuilder(Cat::class.java)
                        .layout(R.layout.item_cat)
                        .binder { vh, cat, payloads -> vh.getView(R.id.name, TextView::class.java).text = cat.name }
                        .build())

                addDelegate(createDelegateBuilder(Dog::class.java)
                        .layout(R.layout.item_dog)
                        .binder { vh, dog -> vh.getView(R.id.name, TextView::class.java).text = dog.name }
                        .build())

                addDelegate(createDelegateBuilder(Gecko::class.java)
                        .layout(R.layout.item_gecko)
                        .binder { vh, gecko ->
                            (vh.getView(R.id.name) as TextView).text = gecko.name
                            (vh.getView(R.id.race) as TextView).text = gecko.race
                        }
                        .build())

                addDelegate(createDelegateBuilder(Snake::class.java)
                        .layout(R.layout.item_snake)
                        .binder { vh, snake ->
                            vh.getView(R.id.name, TextView::class.java).text = snake.name
                            vh.getView(R.id.race, TextView::class.java).text = snake.race
                        }
                        .build())

                setFallbackDelegate(createDelegateBuilder(DisplayableItem::class.java)
                        .layout(android.R.layout.simple_list_item_1)
                        .binder { vh, item, payloads -> vh.getView(android.R.id.text1, TextView::class.java).text = item.toString() }
                        .build())

                items = getAnimals()
            }
        }

    }

    private fun getAnimals(): List<DisplayableItem> {
        val animals = ArrayList<DisplayableItem>()

        animals.add(Cat("American Curl"))
        animals.add(Cat("Baliness"))
        animals.add(Cat("Bengal"))
        animals.add(Cat("Corat"))
        animals.add(Cat("Manx"))
        animals.add(Cat("Nebelung"))
        animals.add(Dog("Aidi"))
        animals.add(Dog("Chinook"))
        animals.add(Dog("Appenzeller"))
        animals.add(Dog("Collie"))
        animals.add(Snake("Mub Adder", "Adder"))
        animals.add(Snake("Texas Blind Snake", "Blind snake"))
        animals.add(Snake("Tree Boa", "Boa"))
        animals.add(Gecko("Fat-tailed", "Hemitheconyx"))
        animals.add(Gecko("Stenodactylus", "Dune Gecko"))
        animals.add(Gecko("Leopard Gecko", "Eublepharis"))
        animals.add(Gecko("Madagascar Gecko", "Phelsuma"))
        animals.add(Advertisement())
        animals.add(Advertisement())
        animals.add(Advertisement())
        animals.add(Advertisement())
        animals.add(Advertisement())

        Collections.shuffle(animals)
        return animals
    }

}
