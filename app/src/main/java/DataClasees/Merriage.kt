package DataClasees

class Merriage {
    private var Boy:String?=null
    private var Girl:String?=null
    private var Age:String?=null
    private var DATE:String?=null
    private var imageurl: String? = null




    constructor()
    {

    }

    constructor(Boy: String?,Girl: String?,Age: String?, DATE: String?,imageurl:String?) {
        this.Boy = Boy
        this.Girl = Girl
        this.Age = Age
        this.DATE = DATE
        this.imageurl = imageurl


    }

    fun getBoy(): String?
    {
        return Boy

    }



    fun setBoy(Boy: String?)
    {
        this.Boy=Boy
    }



    fun getGirl(): String?
    {
        return Girl

    }



    fun setGirl(Girl: String?)
    {
        this.Girl=Girl
    }



    fun getAge(): String?
    {
        return Age

    }



    fun setAge(Age: String?)
    {
        this.Age=Age
    }



    fun getDATE(): String?
    {
        return DATE

    }


    fun setDATE(DATE: String?)
    {
        this.DATE=DATE
    }



    fun getImageurl(): String? {
        return imageurl
    }

    fun setImageurl(imageurl: String?) {
        this.imageurl = imageurl
    }

}