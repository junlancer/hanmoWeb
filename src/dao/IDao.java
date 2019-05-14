package dao;

import java.util.List;

/**
 * @author tb
 * @time 2018/8/14 下午5:02
 * @des 所有crud的抽象
 */
public interface IDao<T> {
    /**
     * 向数据库增加n条数据
     *
     * @param list 需要插入的数据集合
     * @return 返回受影响的条数
     */
    int[] create(List<T> list);

    /**
     * 向数据库查询数据
     *
     * @param o 需要查询的条件
     * @return 返回查到的数据集合
     */
    List<T> read(Object o);

    /**
     * 向数据库更新数据
     *
     * @param list 需要更新的数据集合
     * @return 返回受影响的条数
     */
    int[] update(List<T> list);

    /**
     * 向数据库删除数据
     *
     * @param list 需要删除数据的条件集合
     * @return 返回受影响的条数
     */
    int[] delete(List<Object> list);

    /* start表示开始的个数，count表示取多少条
    *  比如 list(0, 5) ， 即表示第一页，每页有5条数据
    *  比如 list(5, 5) ， 即表示第二页，每页有5条数据*/
    List<T> list(int start, int count);

    int getAllCount();

    void check(List<T> list);
}
