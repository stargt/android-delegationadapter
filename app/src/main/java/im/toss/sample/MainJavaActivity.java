package im.toss.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import im.toss.delegationadapter.ItemViewHolder;
import im.toss.delegationadapter.ListItemDelegationAdapter;
import im.toss.delegationadapter.ViewBinder;
import im.toss.delegationadapter.ViewBinderWithoutPayloads;
import im.toss.sample.model.Advertisement;
import im.toss.sample.model.Cat;
import im.toss.sample.model.DisplayableItem;
import im.toss.sample.model.Dog;
import im.toss.sample.model.Gecko;
import im.toss.sample.model.Snake;

public class MainJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        AnimalAdapter adapter = new AnimalAdapter();
        adapter.setItems(getAnimals());

        rv.setAdapter(adapter);
    }

    class AnimalAdapter extends ListItemDelegationAdapter<DisplayableItem> {

        AnimalAdapter() {

            addDelegate(createDelegateBuilder(Advertisement.class)
                    .layout(R.layout.item_advertisement)
                    .build());

            addDelegate(createDelegateBuilder(Cat.class)
                    .layout(R.layout.item_cat)
                    .binder(new ViewBinder<Cat>() {
                        @Override
                        public void onBindViewHolder(ItemViewHolder vh, @NonNull Cat cat, @NonNull List<Object> payloads) {
                            vh.getView(R.id.name, TextView.class).setText(cat.getName());
                        }
                    })
                    .build());

            addDelegate(createDelegateBuilder(Dog.class)
                    .layout(R.layout.item_dog)
                    .binder(new ViewBinderWithoutPayloads<Dog>() {
                        @Override
                        public void onBindViewHolder(ItemViewHolder vh, @NonNull Dog dog) {
                            vh.getView(R.id.name, TextView.class).setText(dog.getName());
                        }
                    })
                    .build());

            addDelegate(createDelegateBuilder(Gecko.class)
                    .layout(R.layout.item_gecko)
                    .binder(new ViewBinder<Gecko>() {
                        @Override
                        public void onBindViewHolder(ItemViewHolder vh, @NonNull Gecko gecko, @NonNull List<Object> payloads) {
                            vh.getView(R.id.name, TextView.class).setText(gecko.getName());
                            vh.getView(R.id.race, TextView.class).setText(gecko.getRace());
                        }
                    })
                    .build());

            addDelegate(createDelegateBuilder(Snake.class)
                    .layout(R.layout.item_snake)
                    .binder(new ViewBinder<Snake>() {
                        @Override
                        public void onBindViewHolder(ItemViewHolder vh, @NonNull Snake snake, @NonNull List<Object> payloads) {
                            vh.getView(R.id.name, TextView.class).setText(snake.getName());
                            vh.getView(R.id.race, TextView.class).setText(snake.getRace());
                        }
                    })
                    .build());

            setFallbackDelegate(createDelegateBuilder(DisplayableItem.class)
                    .layout(android.R.layout.simple_list_item_1)
                    .binder(new ViewBinder<DisplayableItem>() {
                        @Override
                        public void onBindViewHolder(ItemViewHolder vh, @NonNull DisplayableItem item, @NonNull List<Object> payloads) {
                            vh.getView(android.R.id.text1, TextView.class).setText(item.toString());
                        }
                    })
                    .build());
        }
    }

    private List<DisplayableItem> getAnimals() {
        List<DisplayableItem> animals = new ArrayList<>();

        animals.add(new Cat("American Curl"));
        animals.add(new Cat("Baliness"));
        animals.add(new Cat("Bengal"));
        animals.add(new Cat("Corat"));
        animals.add(new Cat("Manx"));
        animals.add(new Cat("Nebelung"));
        animals.add(new Dog("Aidi"));
        animals.add(new Dog("Chinook"));
        animals.add(new Dog("Appenzeller"));
        animals.add(new Dog("Collie"));
        animals.add(new Snake("Mub Adder", "Adder"));
        animals.add(new Snake("Texas Blind Snake", "Blind snake"));
        animals.add(new Snake("Tree Boa", "Boa"));
        animals.add(new Gecko("Fat-tailed", "Hemitheconyx"));
        animals.add(new Gecko("Stenodactylus", "Dune Gecko"));
        animals.add(new Gecko("Leopard Gecko", "Eublepharis"));
        animals.add(new Gecko("Madagascar Gecko", "Phelsuma"));
        animals.add(new Advertisement());
        animals.add(new Advertisement());
        animals.add(new Advertisement());
        animals.add(new Advertisement());
        animals.add(new Advertisement());

        Collections.shuffle(animals);
        return animals;
    }

}
