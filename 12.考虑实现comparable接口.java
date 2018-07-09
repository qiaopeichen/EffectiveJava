// 例如，考虑BigDecimal类，它的compareTo方法与equals不一致。
// 如果你创建了一个HashSet实例，并且添加new BigDecimal("1.0")和new BigDecimal("1.00")
// 这个集合就将包含两个元素，因为新增到机和中的两个BigDecimal实例， 通过equals方法来比较时是不相等的.
// 然而，如果你使用TreeSet而不是HashSet来执行同样的过程，集合中将只包含一个元素。
// 因为这两个BigDecimal实例在通过compareTo方法进行比较时是相等的。