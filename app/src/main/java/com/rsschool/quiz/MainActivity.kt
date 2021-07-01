package com.rsschool.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), FirstQuizFragment.ActionGenerate,
    SecondQuizFragment.ActionBack, SecondQuizFragment.ActionGenerate,
    ThirdQuizFragment.ActionBack, ThirdQuizFragment.ActionGenerate,
    FourthQuizFragment.ActionBack, FourthQuizFragment.ActionGenerate,
    FifthQuizFragment.ActionBack, FifthQuizFragment.ActionGenerate,
    ResultFragment.ActionBack{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFirstFragment("00000")
    }

    private fun openFirstFragment(result: String) {
        val firstFragment: Fragment = FirstQuizFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun openSecondFragment(result: String) {
        val secondFragment: Fragment = SecondQuizFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
        transaction.commit()
    }

    private fun openThirdFragment(result: String) {
        val thirdFragment: Fragment = ThirdQuizFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, thirdFragment)
        transaction.commit()
    }

    private fun openFourthFragment(result: String) {
        val fourthFragment: Fragment = FourthQuizFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fourthFragment)
        transaction.commit()
    }

    private fun openFifthFragment(result: String) {
        val fifthFragment: Fragment = FifthQuizFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fifthFragment)
        transaction.commit()
    }

    private fun openResultFragment(result: String) {
        val resultFragment: Fragment = ResultFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, resultFragment)
        transaction.commit()
    }

    override fun goToResultFragment( result: String) {
        openResultFragment(result)
    }

    override fun goToFifthFragment( result: String) {
        openFifthFragment(result)
    }

    override fun goToFourthFragment( result: String) {
        openFourthFragment(result)
    }

    override fun goToThirdFragment( result: String) {
        openThirdFragment(result)
    }

    override fun goToSecondFragment( result: String) {
        openSecondFragment(result)
    }

    override fun backToFirstFragment(result: String) {
        openFirstFragment(result)
    }

    override fun backToSecondFragment(result: String) {
        openSecondFragment(result)
    }

    override fun backToThirdFragment(result: String) {
        openThirdFragment(result)
    }

    override fun backToFourthFragment(result: String) {
        openFourthFragment(result)
    }

    override fun backToFifthFragment(result: String) {
        openFifthFragment(result)
    }
}