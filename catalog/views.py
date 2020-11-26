from django.shortcuts import render
from catalog.models import Genre, Book, BookInstance, Author
from django.views import generic
from django.http import Http404

def index(request):
    # view function for homepage

    num_books = Book.objects.all().count()
    num_instances = BookInstance.objects.all().count()

    # Available books
    num_instances_available = BookInstance.objects.filter(
        status__exact='a').count()

    num_authors = Author.objects.count()

    context = {
        'num_books': num_books,
        'num_authors': num_authors,
        'num_instances': num_instances,
        'num_instances_available': num_instances_available,
    }

    # return the rendered
    return render(request, 'index.html', context=context)


# Class base view which inherits from an existing view( which follows django's
# best practice) More robust view with less code.
# Get's all the querry from database for the specified model. Then render a 
# Template in templates/catalog/book_list.html and acces the data using
# _the_model_list
class BookListView(generic.ListView):

    model = Book

    # How to OverridesObject 
    # context_object_name = 'my_book_list'
    # queryset = Book.objects.filter(title__icontians='war')[:5]
    # template_name = 'books/book_list.html'

    # We can also override context 
    def get_context_data(self,**kwargs):
        # The order is important
        # Don't know what is super
        context = super(BookListView, self).get_context_data(**kwargs)
        context['some_data'] = 'This is just some data'
        return context

class BookDetailView(generic.ListView):
    # It will pass the model to your database
    # Template 
    model = Book

# What happens when there is no entry
# def book_detail_view(request,primary_key):
#     try:
#         book = Book.objects.get(pk=primary_key)
#     except Book.DoesNotExists:
#         raise Http404('Book does not exists')

#     return render(request,'catalog/book_detail.html',context={'book' : book})
