package DataClasees

class Note {
    private var Title:String?=null
    private var DESCRIPTION:String?=null


    constructor()
    {

    }

    constructor(Title: String?, DESCRIPTION: String?) {

        this.Title = Title
        this.DESCRIPTION = DESCRIPTION
    }


    fun getTitle(): String?
    {
        return Title

    }

    fun setTitle(Title: String?)
    {
        this.Title=Title
    }

    fun getDESCRIPTION(): String?
    {
        return DESCRIPTION

    }



    fun setDESCRIPTION(DESCRIPTION: String?)
    {
        this.DESCRIPTION=DESCRIPTION
    }



}