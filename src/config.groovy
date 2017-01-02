withConfig(configuration)  {
    ast(groovy.transform.CompileStatic)
    ast(includeNames: true, groovy.transform.ToString)

    imports {
        star('java.time')
    }
}
