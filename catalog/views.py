from django.shortcuts import render
from catalog.models import Genre, Book, BookInstance, Author


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
