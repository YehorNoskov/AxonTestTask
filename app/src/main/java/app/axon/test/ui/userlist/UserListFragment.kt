package app.axon.test.ui.userlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.axon.test.R
import app.axon.test.api.constants.BundleKeys
import app.axon.test.ui.base.BaseFragment
import app.axon.test.ui.base.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_user_list.*


class UserListFragment : BaseFragment<UserListContract.Presenter>(), UserListContract.View {

    private val usersAdapter: UsersAdapter = UsersAdapter()
    private val isLoading = false



    override fun onAttach(context: Context) {
        super.onAttach(context)
        val presenter =
            parentActivity?.getRetrofitClient()?.let { UserListPresenter(it) }
        this.presenter = presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentActivity?.title = getString(R.string.title_home)
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.attach(this)
        presenter?.subscribe()
        presenter?.getJobsList(false)

        rvUsers.adapter = usersAdapter
        val llm = LinearLayoutManager(context)
        rvUsers.layoutManager = llm
        rvUsers.addOnScrollListener(getPagination(llm))

        swipeContainer.setOnRefreshListener { presenter?.refresh() }

        initClickListeners()

    }


    override fun setJobsList(userModelList: List<UserModel>) {
        usersAdapter.setItems(userModelList)
    }

    override fun appendJobsList(userModelList: List<UserModel>) {
        usersAdapter.appendItems(userModelList)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun hideRefresh() {
        if (swipeContainer.isRefreshing) {
            swipeContainer.isRefreshing = false
        }
    }
    private fun getPagination(llm: LinearLayoutManager): PaginationScrollListener {
        return object : PaginationScrollListener(llm) {
            override fun loadMoreItems() {
                presenter?.getJobsList(true)
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        }
    }

    private fun initClickListeners(){
        usersAdapter.setClickListener(object : UsersAdapter.ClickListener{
            override fun onItemClicked(userModel: UserModel) {
                val bundle = Bundle()
                bundle.putParcelable(BundleKeys.USER_INFO, userModel)
                parentActivity?.getNavigationController()?.navigate(R.id.action_userListFragment_to_userDetailsFragment, bundle)
            }

        })
    }

}
