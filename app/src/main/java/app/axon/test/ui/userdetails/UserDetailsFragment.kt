package app.axon.test.ui.userdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.axon.test.R
import app.axon.test.ui.base.BaseFragment
import app.axon.test.utils.ImageLoader
import app.axon.test.utils.setDobFormat
import app.axon.test.utils.setNoActive
import kotlinx.android.synthetic.main.fragment_user_details.*


class UserDetailsFragment : BaseFragment<UserDetailsContract.Presenter>(), UserDetailsContract.View {


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val presenter = UserDetailsPresenter()
        this.presenter = presenter
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        parentActivity?.title = getString(R.string.title_profile)
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.attach(this)
        presenter?.subscribe()

        presenter?.setBundle(arguments)

        initClickListeners()
    }

    override fun showUserAvatar(link: String) {
        ImageLoader.load(link, ivAvatar)
    }

    override fun showUserName(name: String) {
        tvUserName.text = name
    }

    override fun showUserGender(gender: String) {
        tvUserGender.text = gender
    }

    override fun showUserDob(dob: String) {
        edxDob.setText(dob.setDobFormat())
        edxDob.setNoActive()
    }

    override fun showUserCell(cell: String) {
        edxCell.setText(cell)
        edxCell.setNoActive()
    }

    override fun showUserPhone(phone: String) {
        edxPhone.setText(phone)
        edxPhone.setNoActive()
    }


    private fun initClickListeners() {
        edxCell.onFocusChangeListener = View.OnFocusChangeListener { _, _ -> callIntent(edxCell.text.toString()) }
        edxPhone.onFocusChangeListener = View.OnFocusChangeListener { _, _ -> callIntent(edxCell.text.toString()) }
    }


    private fun callIntent(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
        startActivity(intent)
    }
}
