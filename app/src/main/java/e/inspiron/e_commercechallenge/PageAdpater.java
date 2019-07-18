package e.inspiron.e_commercechallenge;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class PageAdpater extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    String id;int tabcount;

    public PageAdpater(FragmentManager manager, String id) {
        super(manager);
        this.id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case (0):
                Buy buy = new Buy(id);
                return buy;
            case (1):
                Order order = new Order(id);
                return order;
            case (2):
                feedback feedback = new feedback(id);
                return feedback;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return (3);
    }


    @Override    public CharSequence getPageTitle(int position) {        switch (position){
        case 0: return "Buy";
        case 1: return "Order";
        case 2:return "FeedBacks";
        default: return null;
    }}}
