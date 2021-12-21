package com.brasileirao.rule

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import com.brasileirao.R
import org.junit.runner.Description
import org.koin.dsl.ModuleDeclaration

class FragmentTestRule<T : Fragment>(
    private val fragmentClass: Class<T>,
    private val bundle: Bundle? = null,
    moduleDeclaration: ModuleDeclaration = {},
): KoinRule(moduleDeclaration) {

    lateinit var scenario: FragmentScenario<T>

    override fun starting(description: Description?) {
        super.starting(description)
        scenario = FragmentScenario.launchInContainer(
            fragmentClass = fragmentClass,
            fragmentArgs = bundle,
            themeResId = R.style.Theme_AppCompat_Light
        )
    }
}