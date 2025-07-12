package com.group4.ui.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.data.model.News;
import com.group4.medgo.R;
import com.group4.ui.common.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsTabFragment extends Fragment {
    private static final String ARG_CATEGORY = "category";
    private String category;

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;

    public NewsTabFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NewsTabFragment newInstance(String category) {
        NewsTabFragment fragment = new NewsTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data - nên thay bằng dữ liệu phân loại theo `category`
        newsList = getNewsByCategory(category);

        adapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);

        return view;

    }

    private List<News> getNewsByCategory(String category) {
        List<News> allNews = new ArrayList<>();

        allNews.add(new News("TPHCM: Sốt xuất huyết gia tăng báo động, 6 ca tử vong",
                "Sốt xuất huyết tại TPHCM đang diễn biến phức tạp...",
                R.drawable.post1, "13/07/2025", "Tin y tế"));

        allNews.add(new News("Chế độ ăn giàu vi chất cho phụ nữ nuôi con bằng sữa mẹ",
                "Cho con bú là giai đoạn vô cùng quan trọng...",
                R.drawable.post2, "12/07/2025", "Mẹo sức khỏe"));

        allNews.add(new News("Cách uống cà phê giúp kiểm soát lượng đường trong máu",
                "Cà phê rất giàu chất chống oxy hóa...",
                R.drawable.post3, "12/07/2025", "Mẹo sức khỏe"));

        allNews.add(new News("Sởi không chỉ là bệnh trẻ em, người lớn cũng có thể tử vong",
                "Sởi là bệnh truyền nhiễm nguy hiểm...",
                R.drawable.post4, "11/07/2025", "Tin y tế"));

        allNews.add(new News("Viêm họng cấp có thể gây ra biến chứng gì?",
                "Viêm họng nếu không điều trị kịp thời...",
                R.drawable.post5, "10/07/2025", "Tin y tế"));

        allNews.add(new News("6 loại thực phẩm giàu vitamin D giúp phòng loãng xương",
                "Vitamin D rất quan trọng cho xương và miễn dịch...",
                R.drawable.post6, "10/07/2025", "Mẹo sức khỏe"));

        // Lọc theo category (nếu là "Tổng hợp" thì hiển thị tất cả)
        if (category.equals("Tổng hợp")) {
            return allNews;
        }

        List<News> filteredList = new ArrayList<>();
        for (News news : allNews) {
            if (news.getNewsCategory().equalsIgnoreCase(category)) {
                filteredList.add(news);
            }
        }
        return filteredList;
    }

    public void filterNews(String query) {
        if (adapter == null) return;

        if (query == null || query.trim().isEmpty()) {
            adapter.updateData(getNewsByCategory(category)); // reset danh sách ban đầu
            return;
        }

        List<News> filteredList = new ArrayList<>();
        for (News news : getNewsByCategory(category)) {
            if (news.getNewsTitle().toLowerCase().contains(query.toLowerCase()) ||
                    news.getNewsSummary().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(news);
            }
        }

        adapter.updateData(filteredList);
    }
}