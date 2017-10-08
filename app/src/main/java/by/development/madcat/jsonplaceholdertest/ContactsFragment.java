package by.development.madcat.jsonplaceholdertest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactsFragment extends Fragment implements View.OnClickListener{
    Unbinder unbinder;

    public ContactsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.phone_fab, R.id.email_fab, R.id.skype_fab})
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.phone_fab:
                intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","+375295449757",null));
                break;
            case R.id.email_fab:
                break;
            case R.id.skype_fab:
                break;
        }

        if(intent != null)
            startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
