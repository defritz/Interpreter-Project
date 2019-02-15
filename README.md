# Interpreter-Project
This is a recursive descent interpreter I developed for my Concepts of Programming Languages course Junior year.
The program prompts the user for an input file containing an expression consisting of boolean arithmetic.
The program then interprets the expression, determining if it is syntactically valid. If valid, it then computes the resulting value.

The interpreter accepts the following syntactical elements:
```
<B> : Bool statement
<IT> : Imply term
<OT> : Or term
<AT> : And term
<IT_Tail> : Imply tail
<OT_Tail> : Or tail
<AT_Tail> : And tail
<L> : Literal
<A> : Atom
```
The interpreter is capable of assesing the value of the following semantic equations:
```
α(<< <IT>. >>) = β(<< <IT> >>)
β(<< <OT><IT Tail> >>) = λ(δ(<< <OT> >>), << <IT Tail> >>)
δ(<< <AT><OT Tail> >>) = µ(γ(<< <AT> >>), << <OT Tail> >>)
γ(<< <L><AT Tail> >>) = η(φ(<< <L> >>), << <AT Tail> >>)
λ(b, ε) = b (w<ere b ∈ {T, F})
λ(F, << − > <OT><IT Tail> >>) = λ(T, << <IT Tail> >>)
λ(T, << − > <OT><IT Tail> >>) = λ(δ(<< <OT> >>), << <IT Tail> >>)
µ(b, ε) = b (w<ere b ∈ {T, F})
µ(T, << ∨<AT><OT Tail> >>) = T
µ(F, << ∨”<AT><OT Tail> >>) = µ(γ(<< <AT> >>), << <OT Tail> >>)
η(b, ε) = b (w<ere b ∈ {T, F})
η(F, << ∧<L><AT Tail> >>) = F
η(T, << ∧<L><AT Tail> >>) = η(φ(<< <L> >>), << <AT Tail> >>)
φ(<<∼ <L> >>) = >f φ(<< <L> >>) = T then F else >f φ(<< <L> >>) = F then T
φ(<< <A> >>) = ψ(<< <A> >>)
ψ(<< T >>) = T
ψ(<< F >>) = F
ψ(<< (<IT>) >>) = (β(<< <IT> >>))
```
