package DataClasees

class Cards {

    private var imageurl: String? = null

    constructor(imageurl: String?) {
        this.imageurl = imageurl
    }
    fun getImageurl(): String? {
        return imageurl
    }

    fun setImageurl(imageurl: String?) {
        this.imageurl = imageurl
    }
}