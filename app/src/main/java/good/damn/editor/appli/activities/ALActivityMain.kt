package good.damn.editor.appli.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import good.damn.editor.appli.fragments.ALFragmentAccount
import good.damn.editor.appli.fragments.ALFragmentEvents
import good.damn.editor.appli.fragments.ALFragmentUniversities

class ALActivityMain
: AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        ViewPager2(
            context
        ).apply {

            val fragments: Array<Fragment> = arrayOf(
                ALFragmentEvents(),
                ALFragmentUniversities(),
                ALFragmentAccount()
            )

            adapter = object: FragmentStateAdapter(
                supportFragmentManager,
                lifecycle
            ) {
                override fun getItemCount() =
                    fragments.size

                override fun createFragment(
                    position: Int
                ) = fragments[
                    position
                ]

            }

            setContentView(
                this
            )
        }

    }

}