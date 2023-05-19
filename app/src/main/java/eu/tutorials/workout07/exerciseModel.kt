package eu.tutorials.workout07

class exerciseModel(
    private var id:Int,
    private var name:String,
    private var image:Int,
    private var isCompleted:Boolean,
    private var isselected:Boolean
    )
{
    fun getid():Int{
        return id
    }
    fun setid(id:Int)
    {
        this.id=id
    }
    fun getname():String{
        return name
    }
    fun setname(name:String)
    {
        this.name=name
    }
    fun getimage():Int{
        return image
    }
    fun setImage(image:Int)
    {
        this.image=image
    }
    fun getisSelected():Boolean{
        return isselected
    }
    fun setIsSelected(isselected:Boolean)
    {
        this.isselected=isselected
    }
    fun getisCompleted():Boolean{
        return isCompleted
    }
    fun setisCompleted(isCompleted:Boolean)
    {
        this.isCompleted=isCompleted
    }
}